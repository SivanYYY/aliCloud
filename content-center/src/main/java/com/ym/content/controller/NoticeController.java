package com.ym.content.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ym.content.commons.enums.ConstantEnum;
import com.ym.content.commons.exceptions.BusinessException;
import com.ym.content.commons.page.Pager;
import com.ym.content.commons.remsg.WebResout;
import com.ym.content.config.ApiBaseController;
import com.ym.content.domain.Notice;
import com.ym.content.domain.dto.NoticeDTO;
import com.ym.content.service.INoticeService;
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
@RequestMapping(value = "//notice", method = RequestMethod.POST)
public class NoticeController extends ApiBaseController {

	@Autowired
	private INoticeService noticeService;

	@PostMapping(value = "save")
	@ResponseBody
	public WebResout saveObj(@ModelAttribute NoticeDTO notice, HttpServletRequest request) throws BusinessException {
		
		logger.debug("NoticeController exe saveObj param={}",notice);
		
		int i = noticeService.insert(notice);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	@GetMapping(value = "listAll")
	@ResponseBody
	public WebResout listAll(HttpServletRequest request) throws BusinessException{
		logger.debug("NoticeController exe listAll ");
		List<NoticeDTO> list =  noticeService.selectAll();
		logger.debug("NoticeController exe listAll out={}",list);
		return success(request,list);
	}
	@GetMapping(value = "getById/{id}")
	@ResponseBody
	public WebResout getById(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		
		logger.debug("NoticeController exe getById?id={}",id);
		
		Notice notice = noticeService.selectById(id);
		if(notice == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		logger.debug("NoticeController exe getById out={} ",notice);
		return success(request,notice);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public WebResout delete(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		logger.debug("NoticeController exe delete?id={}",id);
		
		Notice notice = noticeService.selectById(id);
		if(notice == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		noticeService.deleteById(id);
		logger.debug("NoticeController exe delete?id={}",id);
		
		return success(request);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public WebResout update(HttpServletRequest request,@ModelAttribute NoticeDTO notice) throws BusinessException{
		
		logger.debug("NoticeController exe update?baseAd={}",notice);
		
		int i = noticeService.updateObj(notice);
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
	public WebResout list(HttpServletRequest request, Pager<NoticeDTO> pager,
						  @ModelAttribute NoticeDTO noticeDto) throws BusinessException{
		
		logger.debug("NoticeController exe list?pager={}",pager);
		logger.debug("NoticeController exe list?NoticeDto={}",noticeDto);
		
		pager.setParams(noticeDto);
		noticeService.selectTList(pager);
		
		logger.debug("NoticeController exe list out={}",pager);
		
		return success(request,pager);
	}
}
