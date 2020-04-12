package com.ym.content.service.impl;

import java.util.List;

import com.ym.content.commons.page.Pager;
import com.ym.content.config.base.service.BaseServiceImpl;
import com.ym.content.dao.IMidUserShareDao;
import com.ym.content.domain.dto.MidUserShareDTO;
import com.ym.content.service.IMidUserShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author sivan
 * 功能描述:用户-分享中间表管理
 */
@Service
public class  MidUserShareServiceImpl extends BaseServiceImpl implements IMidUserShareService {


	/*
	//	@Autowired注解Mapper类的时候，因为该类不是spring容器中的，在容器中为null，所以会报红警告，可以使用@Autowired(required = false)来解决

	 */

    private IMidUserShareDao midUserShareDao;
    
    @Override
	public List<MidUserShareDTO> selectAll() {
		logger.info("MidUserShareServiceImpl exe method selectAll");
		List<MidUserShareDTO> list = midUserShareDao.selectAll();
		logger.info("MidUserShareServiceImpl exe method selectAll out:{}",list);
		return list;
	}
	
	@Override
	public MidUserShareDTO selectById(int id) {
		logger.info("MidUserShareServiceImpl exe method selectById?id={}",id);
		MidUserShareDTO t = midUserShareDao.selectById(id);
		logger.info("MidUserShareServiceImpl exe method selectById out:{}",t);
		return t;
	}
	
	@Override
	public MidUserShareDTO selectByUUID(String uuid) {
		logger.info("MidUserShareServiceImpl exe method selectByUUID?uuid={}",uuid);
		MidUserShareDTO t = midUserShareDao.selectByUUID(uuid);
		logger.info("MidUserShareServiceImpl exe method selectByUUID out:{}",t);
		return t;
	}
	
	@Override
	public void selectTList(Pager<MidUserShareDTO> pager) {
		logger.info("MidUserShareServiceImpl exe method selectTList?pager={}",pager);
		
		int totalCount = midUserShareDao.selectTListCount(pager);
		pager.setTotalCount(totalCount);
		
		setPageParam(pager);
		
		List<MidUserShareDTO> list = midUserShareDao.selectTList(pager);
		pager.setList(list);
		
		logger.info("BaseAdServiceImpl exe method selectTList?out={}",pager);
	}
	
	@Override
	public void deleteById(int id) {
		logger.info("MidUserShareServiceImpl exe method deleteById?id={}",id);
		
		midUserShareDao.deleteById(id);
		
		logger.info("MidUserShareServiceImpl exe method deleteById");
	}
	
	@Override
	public int insert(MidUserShareDTO t) {
		logger.info("MidUserShareServiceImpl exe method insert?t={}",t);
		
		int i = midUserShareDao.insert(t);
		
		logger.info("MidUserShareServiceImpl exe method insert out={}",i);
		return i;
	}
	
	@Override
	public int updateObj(MidUserShareDTO t) {
		logger.info("MidUserShareServiceImpl exe method updateObj?t={}",t);
		
		int i = midUserShareDao.updateObj(t);
		
		logger.info("MidUserShareServiceImpl exe method updateObj out={}",i);
		return i;
	}

}
