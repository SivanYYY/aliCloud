package com.ym.content.service.impl;

import com.ym.content.commons.page.Pager;
import com.ym.content.config.base.service.BaseServiceImpl;
import com.ym.content.config.feign.UserCenterFeignClient;
import com.ym.content.dao.IShareDao;
import com.ym.content.domain.dto.ShareDTO;
import com.ym.content.domain.dto.UserDTO;
import com.ym.content.service.IShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;


/**
 * @author sivan
 * 功能描述:分享表管理
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl extends BaseServiceImpl implements IShareService {


    private final IShareDao shareDao;

    private final RestTemplate restTemplate;

    private final UserCenterFeignClient userCenterFeignClient;

    private final DiscoveryClient discoveryClient;


    @Override
    public List<ShareDTO> selectAll() {
        logger.info("ShareServiceImpl exe method selectAll");
        List<ShareDTO> list = shareDao.selectAll();
        logger.info("ShareServiceImpl exe method selectAll out:{}", list);
        return list;
    }

    @Override
    public ShareDTO selectById(int id) {
        logger.info("ShareServiceImpl exe method selectById?id={}", id);
        ShareDTO t = shareDao.selectById(id);
        Long userId = t.getUserId();
/*        // 调用user-center接口获取发布人信息 使用spring轻量级RestTemplate
        // 获取用户中心实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
         *//* // jdk1.8写法 stream lambda表达式 functional函数式编程
        String targetUrl = instances.stream()
                .map(instance -> instance.getUri().toString() + "/api/user/1.0/view?id=" + userId)
                .findFirst()
                .orElseThrow(() -> new ExpressionException("当前没有实例！"));
        logger.info("==========>请求的URL{}<==========，", targetUrl);
        // 获取当前注册的服务实例
        List<String> services = discoveryClient.getServices();
        logger.info("==========>获取的实例{}<==========，", services);
*//*
        // 获取实例集合
        List<String> targetUrlList = instances.stream()
                .map(instance -> instance.getUri().toString() + "/api/user/1.0/view?id=" + userId)
                .collect(Collectors.toList());
        // 随机集合下标
        int i = ThreadLocalRandom.current().nextInt(targetUrlList.size());
        logger.info("==========>请求的URL{}<==========，", targetUrlList.get(i));
//		String url = "http://localhost:9527/api/user/1.0/view?id=" + userId;
//		WebResout forEntity = restTemplate.getForObject(url, WebResout.class);
//		UserDTO userDTO=(UserDTO) forEntity.getData();
//        UserDTO userDTO = restTemplate.getForObject(targetUrl, UserDTO.class);
        UserDTO userDTO = restTemplate.getForObject(targetUrlList.get(i), UserDTO.class);*/
        // 这里整合Ribbon之后就不使用上方注释的写法了，直接按照如下写法即可，Ribb会自动去寻找user-center服务的地址
//        UserDTO userDTO=restTemplate.getForObject("http://user-center/api/user/1.0/view?id={userId}",UserDTO.class,userId);
        // 使用feign构造请求，请求更加优雅，基本上看不出来是在进行远程调用
        UserDTO userDTO = this.userCenterFeignClient.findById(userId);

        // 消息的装配
        ShareDTO shareDTO = new ShareDTO();
        // 复制方法BeanUtils.copyProperties
        BeanUtils.copyProperties(t, shareDTO);
        if (StringUtil.isNotEmpty(userDTO.getWxNickname())) {
            shareDTO.setWxNickname(userDTO.getWxNickname());
        }

        logger.info("ShareServiceImpl exe method selectById out:{}", t);
        return shareDTO;
    }

    @Override
    public ShareDTO selectByUUID(String uuid) {
        logger.info("ShareServiceImpl exe method selectByUUID?uuid={}", uuid);
        ShareDTO t = shareDao.selectByUUID(uuid);
        logger.info("ShareServiceImpl exe method selectByUUID out:{}", t);
        return t;
    }

    @Override
    public void selectTList(Pager<ShareDTO> pager) {
        logger.info("ShareServiceImpl exe method selectTList?pager={}", pager);

        int totalCount = shareDao.selectTListCount(pager);
        pager.setTotalCount(totalCount);

        setPageParam(pager);

        List<ShareDTO> list = shareDao.selectTList(pager);
        pager.setList(list);

        logger.info("BaseAdServiceImpl exe method selectTList?out={}", pager);
    }

    @Override
    public void deleteById(int id) {
        logger.info("ShareServiceImpl exe method deleteById?id={}", id);

        shareDao.deleteById(id);

        logger.info("ShareServiceImpl exe method deleteById");
    }

    @Override
    public int insert(ShareDTO t) {
        logger.info("ShareServiceImpl exe method insert?t={}", t);

        int i = shareDao.insert(t);

        logger.info("ShareServiceImpl exe method insert out={}", i);
        return i;
    }

    @Override
    public int updateObj(ShareDTO t) {
        logger.info("ShareServiceImpl exe method updateObj?t={}", t);

        int i = shareDao.updateObj(t);

        logger.info("ShareServiceImpl exe method updateObj out={}", i);
        return i;
    }

}
