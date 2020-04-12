package com.ym.content.dao;

import com.ym.content.config.base.dao.BaseDao;
import com.ym.content.domain.dto.ShareDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author sivan
 * 功能描述:分享表管理
 */
@Mapper
@Repository
public interface IShareDao extends BaseDao<ShareDTO> {
	
	
}
