package com.ym.content.config.feign;

import com.ym.content.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "user-center",configuration = UserCenterFeignClientConfiguration.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {

    /**
     * feign或构造请求 http://user-center/api/user/1.0/view?id={userId},并且自动请求
     * @return
     */
    @GetMapping("/api/user/1.0/view?id={userId}")
    UserDTO findById(@PathVariable long userId);
}
