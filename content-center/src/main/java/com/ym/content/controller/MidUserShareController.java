package com.ym.content.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ym.content.commons.enums.ConstantEnum;
import com.ym.content.commons.exceptions.BusinessException;
import com.ym.content.commons.page.Pager;
import com.ym.content.commons.remsg.WebResout;
import com.ym.content.config.ApiBaseController;
import com.ym.content.domain.MidUserShare;
import com.ym.content.domain.dto.MidUserShareDTO;
import com.ym.content.service.IMidUserShareService;
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



@Controller
@RequestMapping(value = "//midUserShare", method = RequestMethod.POST)
public class MidUserShareController extends ApiBaseController {

	@Autowired
	private IMidUserShareService midUserShareService;

	@PostMapping(value = "save")
	@ResponseBody
	public WebResout saveObj(@ModelAttribute MidUserShareDTO midUserShare, HttpServletRequest request) throws BusinessException{
		
		logger.debug("MidUserShareController exe saveObj param={}",midUserShare);
		
		int i = midUserShareService.insert(midUserShare);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	@GetMapping(value = "listAll")
	@ResponseBody
	public WebResout listAll(HttpServletRequest request) throws BusinessException {
		logger.debug("MidUserShareController exe listAll ");
		List<MidUserShareDTO> list =  midUserShareService.selectAll();
		logger.debug("MidUserShareController exe listAll out={}",list);
		return success(request,list);
	}
	@GetMapping(value = "getById/{id}")
	@ResponseBody
	public WebResout getById(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		
		logger.debug("MidUserShareController exe getById?id={}",id);
		
		MidUserShare midUserShare = midUserShareService.selectById(id);
		if(midUserShare == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		logger.debug("MidUserShareController exe getById out={} ",midUserShare);
		return success(request,midUserShare);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public WebResout delete(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		logger.debug("MidUserShareController exe delete?id={}",id);
		
		MidUserShare midUserShare = midUserShareService.selectById(id);
		if(midUserShare == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		midUserShareService.deleteById(id);
		logger.debug("MidUserShareController exe delete?id={}",id);
		
		return success(request);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public WebResout update(HttpServletRequest request,@ModelAttribute MidUserShareDTO midUserShare) throws BusinessException{
		
		logger.debug("MidUserShareController exe update?baseAd={}",midUserShare);
		
		int i = midUserShareService.updateObj(midUserShare);
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
	public WebResout list(HttpServletRequest request, Pager<MidUserShareDTO> pager,
						  @ModelAttribute MidUserShareDTO midUserShareDto) throws BusinessException{
		
		logger.debug("MidUserShareController exe list?pager={}",pager);
		logger.debug("MidUserShareController exe list?MidUserShareDto={}",midUserShareDto);
		
		pager.setParams(midUserShareDto);
		midUserShareService.selectTList(pager);
		
		logger.debug("MidUserShareController exe list out={}",pager);
		
		return success(request,pager);
	}
}
