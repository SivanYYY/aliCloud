package com.ym.content.config.feign;

import com.ym.content.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MaYue
 * @Date 2020/4/8 22:06
 * @Describe 测试多参数请求构造
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {


    @GetMapping("/api/user/1.0/q")
    public UserDTO query(@SpringQueryMap UserDTO userDTO);

}
