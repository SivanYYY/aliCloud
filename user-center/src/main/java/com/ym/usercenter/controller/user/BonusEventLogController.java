package com.ym.usercenter.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ym.usercenter.commons.remsg.WebResout;
import com.ym.usercenter.config.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ym.usercenter.commons.enums.ConstantEnum;
import com.ym.usercenter.commons.exceptions.BusinessException;
import com.ym.usercenter.commons.page.Pager;
import com.ym.usercenter.domain.user.BonusEventLog;
import com.ym.usercenter.domain.user.dto.BonusEventLogDTO;
import com.ym.usercenter.service.user.IBonusEventLogService;


@Controller
@RequestMapping(value = "/user/bonusEventLog", method = RequestMethod.POST)
public class BonusEventLogController extends ApiBaseController {

	@Autowired
	private IBonusEventLogService bonusEventLogService;

	@PostMapping(value = "save")
	@ResponseBody
	public WebResout saveObj(@ModelAttribute BonusEventLogDTO bonusEventLog, HttpServletRequest request) throws BusinessException{
		
		logger.debug("BonusEventLogController exe saveObj param={}",bonusEventLog);
		
		int i = bonusEventLogService.insert(bonusEventLog);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	@GetMapping(value = "listAll")
	@ResponseBody
	public WebResout listAll(HttpServletRequest request) throws BusinessException{
		logger.debug("BonusEventLogController exe listAll ");
		List<BonusEventLogDTO> list =  bonusEventLogService.selectAll();
		logger.debug("BonusEventLogController exe listAll out={}",list);
		return success(request,list);
	}
	@GetMapping(value = "getById/{id}")
	@ResponseBody
	public WebResout getById(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		
		logger.debug("BonusEventLogController exe getById?id={}",id);
		
		BonusEventLog bonusEventLog = bonusEventLogService.selectById(id);
		if(bonusEventLog == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		logger.debug("BonusEventLogController exe getById out={} ",bonusEventLog);
		return success(request,bonusEventLog);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public WebResout delete(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		logger.debug("BonusEventLogController exe delete?id={}",id);
		
		BonusEventLog bonusEventLog = bonusEventLogService.selectById(id);
		if(bonusEventLog == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		bonusEventLogService.deleteById(id);
		logger.debug("BonusEventLogController exe delete?id={}",id);
		
		return success(request);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public WebResout update(HttpServletRequest request,@ModelAttribute BonusEventLogDTO bonusEventLog) throws BusinessException{
		
		logger.debug("BonusEventLogController exe update?baseAd={}",bonusEventLog);
		
		int i = bonusEventLogService.updateObj(bonusEventLog);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	/**
	 * currentPage: 当前页码
	   numPerPage：每页显示条数
     **/
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public WebResout list(HttpServletRequest request,Pager<BonusEventLogDTO> pager, 
						@ModelAttribute BonusEventLogDTO bonusEventLogDto) throws BusinessException{
		
		logger.debug("BonusEventLogController exe list?pager={}",pager);
		logger.debug("BonusEventLogController exe list?BonusEventLogDto={}",bonusEventLogDto);
		
		pager.setParams(bonusEventLogDto);
		bonusEventLogService.selectTList(pager);
		
		logger.debug("BonusEventLogController exe list out={}",pager);
		
		return success(request,pager);
	}
}
