package com.ym.content.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MaYue
 * @Date 2020/4/8 22:57
 * @Describe Feign脱离Ribbon使用，调用百度
 */
@FeignClient(name = "baidu",url = "www.baidu.com")
public interface TestBaiduFeignClient {

    @GetMapping("")
    public String index();

}
