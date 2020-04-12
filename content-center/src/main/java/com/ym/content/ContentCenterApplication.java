package com.ym.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Sivan
 */
@MapperScan("com.ym.content.dao.*")
@SpringBootApplication
@EnableFeignClients //(defaultConfiguration = GlobelFeignClientConfiguration.class)
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }



    /**
     *  // 使用ioc特性
     *  // 在spring容器中创建一个对象，类型为RestTemplate，名称/ID为restTemplate
     *  // 相当于bean标签<bean id="restTemplate" class="xxx.RestTemplate"
     * @return
     */
    @Bean
    @LoadBalanced // Ribbon注解 为RestTemplate整合Ribbon
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
