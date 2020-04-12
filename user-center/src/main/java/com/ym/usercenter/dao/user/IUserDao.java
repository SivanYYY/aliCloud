package com.ym.usercenter.dao.user;

import com.ym.usercenter.config.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import com.ym.usercenter.domain.user.dto.UserDTO;

/**
 * @author sivan
 * 功能描述:用户表管理
 */
@Mapper
public interface IUserDao extends BaseDao<UserDTO> {

    public void dd();
	
}
