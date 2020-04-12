package com.ym.usercenter.service.user.impl;

import java.util.List;

import com.ym.usercenter.config.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ym.usercenter.dao.user.IUserDao;
import com.ym.usercenter.domain.user.dto.UserDTO;
import com.ym.usercenter.service.user.IUserService;
import com.ym.usercenter.commons.page.Pager;

/**
 * @author sivan
 * 功能描述:用户表管理
 */
@Service
public class  UserServiceImpl extends BaseServiceImpl implements IUserService {
	

	@Autowired
    private IUserDao userDao;
    
    @Override
	public List<UserDTO> selectAll() {
		logger.info("UserServiceImpl exe method selectAll");
		List<UserDTO> list = userDao.selectAll();
		logger.info("UserServiceImpl exe method selectAll out:{}",list);
		return list;
	}
	
	@Override
	public UserDTO selectById(int id) {
		logger.info("UserServiceImpl exe method selectById?id={}",id);
		UserDTO t = userDao.selectById(id);
		logger.info("UserServiceImpl exe method selectById out:{}",t);
		return t;
	}
	
	@Override
	public UserDTO selectByUUID(String uuid) {
		logger.info("UserServiceImpl exe method selectByUUID?uuid={}",uuid);
		UserDTO t = userDao.selectByUUID(uuid);
		logger.info("UserServiceImpl exe method selectByUUID out:{}",t);
		return t;
	}
	
	@Override
	public void selectTList(Pager<UserDTO> pager) {
		logger.info("UserServiceImpl exe method selectTList?pager={}",pager);
		
		int totalCount = userDao.selectTListCount(pager);
		pager.setTotalCount(totalCount);
		
		setPageParam(pager);
		
		List<UserDTO> list = userDao.selectTList(pager);
		pager.setList(list);
		
		logger.info("BaseAdServiceImpl exe method selectTList?out={}",pager);
	}
	
	@Override
	public void deleteById(int id) {
		logger.info("UserServiceImpl exe method deleteById?id={}",id);
		
		userDao.deleteById(id);
		
		logger.info("UserServiceImpl exe method deleteById");
	}
	
	@Override
	public int insert(UserDTO t) {
		logger.info("UserServiceImpl exe method insert?t={}",t);
		
		int i = userDao.insert(t);
		
		logger.info("UserServiceImpl exe method insert out={}",i);
		return i;
	}
	
	@Override
	public int updateObj(UserDTO t) {
		logger.info("UserServiceImpl exe method updateObj?t={}",t);
		
		int i = userDao.updateObj(t);
		
		logger.info("UserServiceImpl exe method updateObj out={}",i);
		return i;
	}

}
