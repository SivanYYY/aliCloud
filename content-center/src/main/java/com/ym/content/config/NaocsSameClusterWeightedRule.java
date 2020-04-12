package com.ym.content.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 同集群优先规则
 */
@Slf4j
public class NaocsSameClusterWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            // 获取配置文件中配置的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            // 获取微服务名称
            String name = loadBalancer.getName();
            // 拿取服务发现相关的API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            //1，找到指定服务的所有实例A，只拿健康的实例
            List<Instance> instances = namingService.selectInstances(name, true);
            //2，过滤出相同集群下的所有实例B
            List<Instance> sameClusterInstances = instances.stream()
                    .filter(instance -> Objects.equals(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());
            //3，如果B是空，就用A
            List<Instance> instanceList = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameClusterInstances)) {
                instanceList = instances;
                log.warn("发生跨集群的调用 name={}, clusterName={},instance={}",
                        name
                        , clusterName
                        , instances);
            } else {
                instanceList = sameClusterInstances;
            }
            //4，基于权重的负载均衡算法，返回一个实例
            Instance instance = ExtendBalance.getHostByRandomWeightCopy(instanceList);
            log.info("选择的实例是 port={} clusterName={}", instance.getPort(), instance.getClusterName());
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("发生了异常", e);
            return null;
        }
    }
}

/**
 * 调用nacos内置的负载均衡算法
 */
class ExtendBalance extends Balancer {
    public static Instance getHostByRandomWeightCopy(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
