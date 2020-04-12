package com.ym.content.service.impl;

import java.util.List;

import com.ym.content.commons.page.Pager;
import com.ym.content.config.base.service.BaseServiceImpl;
import com.ym.content.dao.INoticeDao;
import com.ym.content.domain.dto.NoticeDTO;
import com.ym.content.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sivan
 * 功能描述:提示表管理
 */
@Service
public class  NoticeServiceImpl extends BaseServiceImpl implements INoticeService {
	

	@Autowired
    private INoticeDao noticeDao;
    
    @Override
	public List<NoticeDTO> selectAll() {
		logger.info("NoticeServiceImpl exe method selectAll");
		List<NoticeDTO> list = noticeDao.selectAll();
		logger.info("NoticeServiceImpl exe method selectAll out:{}",list);
		return list;
	}
	
	@Override
	public NoticeDTO selectById(int id) {
		logger.info("NoticeServiceImpl exe method selectById?id={}",id);
		NoticeDTO t = noticeDao.selectById(id);
		logger.info("NoticeServiceImpl exe method selectById out:{}",t);
		return t;
	}
	
	@Override
	public NoticeDTO selectByUUID(String uuid) {
		logger.info("NoticeServiceImpl exe method selectByUUID?uuid={}",uuid);
		NoticeDTO t = noticeDao.selectByUUID(uuid);
		logger.info("NoticeServiceImpl exe method selectByUUID out:{}",t);
		return t;
	}
	
	@Override
	public void selectTList(Pager<NoticeDTO> pager) {
		logger.info("NoticeServiceImpl exe method selectTList?pager={}",pager);
		
		int totalCount = noticeDao.selectTListCount(pager);
		pager.setTotalCount(totalCount);
		
		setPageParam(pager);
		
		List<NoticeDTO> list = noticeDao.selectTList(pager);
		pager.setList(list);
		
		logger.info("BaseAdServiceImpl exe method selectTList?out={}",pager);
	}
	
	@Override
	public void deleteById(int id) {
		logger.info("NoticeServiceImpl exe method deleteById?id={}",id);
		
		noticeDao.deleteById(id);
		
		logger.info("NoticeServiceImpl exe method deleteById");
	}
	
	@Override
	public int insert(NoticeDTO t) {
		logger.info("NoticeServiceImpl exe method insert?t={}",t);
		
		int i = noticeDao.insert(t);
		
		logger.info("NoticeServiceImpl exe method insert out={}",i);
		return i;
	}
	
	@Override
	public int updateObj(NoticeDTO t) {
		logger.info("NoticeServiceImpl exe method updateObj?t={}",t);
		
		int i = noticeDao.updateObj(t);
		
		logger.info("NoticeServiceImpl exe method updateObj out={}",i);
		return i;
	}

}
