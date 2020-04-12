package com.ym.content.config.feign.Configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author MaYue
 * @Date 2020/4/7 11:36
 * @Describe Feign日志级别实现
 * 如果这个类是放在和启动类一下目录下面就不用加上@Configuration注解，不然会出现父子上下文扫描问题
 */
public class GlobelFeignClientConfiguration {

    @Bean
    public Logger.Level level(){
        // 让Feign打印请求的所有细节
        return Logger.Level.FULL;
    }

}
