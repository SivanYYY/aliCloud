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
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据版本控制规则
 */
@Slf4j
public class NacosVersionRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            // 获取配置的版本信息
            String allowVersion = nacosDiscoveryProperties.getMetadata().get("target-version");

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            // 获取微服务名称
            String name = loadBalancer.getName();
            // 拿取服务发现相关的API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            // 发现指定的服务列表
            List<Instance> instances = namingService.selectInstances(name, true);
            // 元数据版本筛选
            /** 传统写法*/
//            List<Instance> newInstances = new ArrayList<>();
//            for (Instance i : instances) {
//                if (i.getMetadata().get("version").equals(allowVersion)) {
//                    newInstances.add(i);
//                }
//            }
            /** lambda写法 更简洁*/
            List<Instance> collectList = instances.stream()
                    .filter(instance -> ObjectUtils.equals(instance.getMetadata().get("version"), allowVersion))
                    .collect(Collectors.toList());
            // 如果筛选出来的为空 就使用未筛选的值
            List<Instance> instanceList = new ArrayList<>();
            if (CollectionUtils.isEmpty(collectList)) {
                instanceList = instances;
                log.warn("没有对应的微服务版本，发生其他版本调用name={} instance={}", name, instances);
            } else {
                instanceList = collectList;
            }
            // 基于权重负载均衡算法，返回一个实例
            Instance instance = ExtendBalance1.getHostByRandomWeightCopy(instanceList);
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
class ExtendBalance1 extends Balancer {
    public static Instance getHostByRandomWeightCopy(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
