package com.ym.content.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ym.content.commons.enums.ConstantEnum;
import com.ym.content.commons.exceptions.BusinessException;
import com.ym.content.commons.page.Pager;
import com.ym.content.commons.remsg.WebResout;
import com.ym.content.config.ApiBaseController;
import com.ym.content.domain.Share;
import com.ym.content.domain.dto.ShareDTO;
import com.ym.content.service.IShareService;
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
@RequestMapping(value = "//share", method = RequestMethod.POST)
public class ShareController extends ApiBaseController {

	@Autowired
	private IShareService shareService;

	@PostMapping(value = "save")
	@ResponseBody
	public WebResout saveObj(@ModelAttribute ShareDTO share, HttpServletRequest request) throws BusinessException {
		
		logger.debug("ShareController exe saveObj param={}",share);
		
		int i = shareService.insert(share);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	@GetMapping(value = "listAll")
	@ResponseBody
	public WebResout listAll(HttpServletRequest request) throws BusinessException{
		logger.debug("ShareController exe listAll ");
		List<ShareDTO> list =  shareService.selectAll();
		logger.debug("ShareController exe listAll out={}",list);
		return success(request,list);
	}
	@GetMapping(value = "getById/{id}")
	@ResponseBody
	public WebResout getById(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		
		logger.debug("ShareController exe getById?id={}",id);
		
		Share share = shareService.selectById(id);
		if(share == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		logger.debug("ShareController exe getById out={} ",share);
		return success(request,share);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public WebResout delete(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		logger.debug("ShareController exe delete?id={}",id);
		
		Share share = shareService.selectById(id);
		if(share == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		shareService.deleteById(id);
		logger.debug("ShareController exe delete?id={}",id);
		
		return success(request);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public WebResout update(HttpServletRequest request,@ModelAttribute ShareDTO share) throws BusinessException{
		
		logger.debug("ShareController exe update?baseAd={}",share);
		
		int i = shareService.updateObj(share);
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
	public WebResout list(HttpServletRequest request, Pager<ShareDTO> pager,
						  @ModelAttribute ShareDTO shareDto) throws BusinessException{
		
		logger.debug("ShareController exe list?pager={}",pager);
		logger.debug("ShareController exe list?ShareDto={}",shareDto);
		
		pager.setParams(shareDto);
		shareService.selectTList(pager);
		
		logger.debug("ShareController exe list out={}",pager);
		
		return success(request,pager);
	}
}
