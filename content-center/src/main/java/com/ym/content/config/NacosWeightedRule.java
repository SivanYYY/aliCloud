package com.ym.content.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties discoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // 加载配置文件的方法，并初始化NacosWeightedRule的方法
    }

    @Override
    public Server choose(Object o) {
        try {
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            log.info("获得的loadBalancer={loadBalancer}", loadBalancer);
            // 请求的微服务名称
            String name = loadBalancer.getName();

            // 拿到服务发现相关的API
            NamingService namingService = discoveryProperties.namingServiceInstance();

            // nacos client 自动通过基于权重的负载均衡算法，选择一个实例
            Instance instance = namingService.selectOneHealthyInstance(name);

            log.info("选择的实例是：port={},instance={}",instance.getPort(),instance);
            return new NacosServer(instance);

        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }

    }
}
