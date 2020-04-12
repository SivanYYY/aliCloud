package com.ym.content.dao;

import com.ym.content.config.base.dao.BaseDao;
import com.ym.content.domain.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author sivan
 * 功能描述:提示表管理
 */
@Mapper
public interface INoticeDao extends BaseDao<NoticeDTO> {
	
	
}
