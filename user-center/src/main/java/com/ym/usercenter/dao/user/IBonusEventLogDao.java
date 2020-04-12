package com.ym.usercenter.dao.user;

import com.ym.usercenter.config.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import com.ym.usercenter.domain.user.dto.BonusEventLogDTO;

/**
 * @author sivan
 * 功能描述:积分表管理
 */
@Mapper
public interface IBonusEventLogDao extends BaseDao<BonusEventLogDTO> {
	
	
}
