package com.ym.usercenter.service.user.impl;

import java.util.List;

import com.ym.usercenter.config.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ym.usercenter.dao.user.IBonusEventLogDao;
import com.ym.usercenter.domain.user.dto.BonusEventLogDTO;
import com.ym.usercenter.service.user.IBonusEventLogService;
import com.ym.usercenter.commons.page.Pager;

/**
 * @author sivan
 * 功能描述:积分表管理
 */
@Service
public class  BonusEventLogServiceImpl extends BaseServiceImpl implements IBonusEventLogService {
	

	@Autowired
    private IBonusEventLogDao bonusEventLogDao;
    
    @Override
	public List<BonusEventLogDTO> selectAll() {
		logger.info("BonusEventLogServiceImpl exe method selectAll");
		List<BonusEventLogDTO> list = bonusEventLogDao.selectAll();
		logger.info("BonusEventLogServiceImpl exe method selectAll out:{}",list);
		return list;
	}
	
	@Override
	public BonusEventLogDTO selectById(int id) {
		logger.info("BonusEventLogServiceImpl exe method selectById?id={}",id);
		BonusEventLogDTO t = bonusEventLogDao.selectById(id);
		logger.info("BonusEventLogServiceImpl exe method selectById out:{}",t);
		return t;
	}
	
	@Override
	public BonusEventLogDTO selectByUUID(String uuid) {
		logger.info("BonusEventLogServiceImpl exe method selectByUUID?uuid={}",uuid);
		BonusEventLogDTO t = bonusEventLogDao.selectByUUID(uuid);
		logger.info("BonusEventLogServiceImpl exe method selectByUUID out:{}",t);
		return t;
	}
	
	@Override
	public void selectTList(Pager<BonusEventLogDTO> pager) {
		logger.info("BonusEventLogServiceImpl exe method selectTList?pager={}",pager);
		
		int totalCount = bonusEventLogDao.selectTListCount(pager);
		pager.setTotalCount(totalCount);
		
		setPageParam(pager);
		
		List<BonusEventLogDTO> list = bonusEventLogDao.selectTList(pager);
		pager.setList(list);
		
		logger.info("BaseAdServiceImpl exe method selectTList?out={}",pager);
	}
	
	@Override
	public void deleteById(int id) {
		logger.info("BonusEventLogServiceImpl exe method deleteById?id={}",id);
		
		bonusEventLogDao.deleteById(id);
		
		logger.info("BonusEventLogServiceImpl exe method deleteById");
	}
	
	@Override
	public int insert(BonusEventLogDTO t) {
		logger.info("BonusEventLogServiceImpl exe method insert?t={}",t);
		
		int i = bonusEventLogDao.insert(t);
		
		logger.info("BonusEventLogServiceImpl exe method insert out={}",i);
		return i;
	}
	
	@Override
	public int updateObj(BonusEventLogDTO t) {
		logger.info("BonusEventLogServiceImpl exe method updateObj?t={}",t);
		
		int i = bonusEventLogDao.updateObj(t);
		
		logger.info("BonusEventLogServiceImpl exe method updateObj out={}",i);
		return i;
	}

}
