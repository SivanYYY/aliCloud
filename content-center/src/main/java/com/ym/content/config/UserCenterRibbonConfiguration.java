package com.ym.content.config;


import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;
/**
 * 指定配置
 */
//@Configuration
//@RibbonClient(name = "user-center",configuration = RibbonConfiguration.class)


/**
 * 全局配置写法
 */
@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
