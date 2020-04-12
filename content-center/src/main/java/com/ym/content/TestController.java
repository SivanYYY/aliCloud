package com.ym.content;

import com.ym.content.config.feign.TestBaiduFeignClient;
import com.ym.content.config.feign.TestUserCenterFeignClient;
import com.ym.content.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;

/**
 * @author MaYue
 * @Discripe 测试
 */
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 测试：服务发现，内容中心总能找到用户中心
     *
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("/getInstances")
    public List<ServiceInstance> getInstance() {
        //查询指定服务的实例
        return discoveryClient.getInstances("user-center");
    }

    /**
     * 测试： 服务发现，查询当前注册的所有服务的服务id
     *
     * @return 当前注册的所有服务的服务id
     */
    @GetMapping("getServers")
    public List<String> getServers() {
        return discoveryClient.getServices();
    }


    @Autowired
    private TestUserCenterFeignClient testUserCenterFeignClient;

    @GetMapping("/test-server")
    public UserDTO query(UserDTO userDTO) {
        return this.testUserCenterFeignClient.query(userDTO);
    }

    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("/baidu")
    public String baiduIndex(){
        return testBaiduFeignClient.index();
    }











    public static void main(String[] args) {
        List<String> list = new Vector<>();
    }



}
