package com.ym.content.dao;

import com.ym.content.config.base.dao.BaseDao;
import com.ym.content.domain.dto.MidUserShareDTO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author sivan
 * 功能描述:用户-分享中间表管理
 */
@Mapper
public interface IMidUserShareDao extends BaseDao<MidUserShareDTO> {
	
	
}
