package com.ym.usercenter.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ym.usercenter.commons.remsg.WebResout;
import com.ym.usercenter.config.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
import com.ym.usercenter.domain.user.User;
import com.ym.usercenter.domain.user.dto.UserDTO;
import com.ym.usercenter.service.user.IUserService;


@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController extends ApiBaseController {

	@Autowired
	private IUserService userService;

	@PostMapping(value = "save")
	@ResponseBody
	public WebResout saveObj(@ModelAttribute UserDTO user, HttpServletRequest request) throws BusinessException{
		
		logger.debug("UserController exe saveObj param={}",user);
		
		int i = userService.insert(user);
		if(i > 0) {
			return success(request);
		}
		return fail(request);
	}
	
	@GetMapping(value = "listAll")
	@ResponseBody
	public WebResout listAll(HttpServletRequest request) throws BusinessException{
		logger.debug("UserController exe listAll ");
		List<UserDTO> list =  userService.selectAll();
		logger.debug("UserController exe listAll out={}",list);
		return success(request,list);
	}
	@GetMapping(value = "getById/{id}")
	@ResponseBody
	public WebResout getById(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		
		logger.debug("UserController exe getById?id={}",id);
		
		User user = userService.selectById(id);
		if(user == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		logger.debug("UserController exe getById out={} ",user);
		return success(request,user);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public WebResout delete(HttpServletRequest request,@PathVariable int id) throws BusinessException{
		logger.debug("UserController exe delete?id={}",id);
		
		User user = userService.selectById(id);
		if(user == null) {
			return fail(request, ConstantEnum._WEB_DTAT_IS_NULL.getVal(), ConstantEnum._WEB_DTAT_IS_NULL.getDesc());
		}
		userService.deleteById(id);
		logger.debug("UserController exe delete?id={}",id);
		
		return success(request);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public WebResout update(HttpServletRequest request,@ModelAttribute UserDTO user) throws BusinessException{
		
		logger.debug("UserController exe update?baseAd={}",user);
		
		int i = userService.updateObj(user);
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
	public WebResout list(HttpServletRequest request,Pager<UserDTO> pager, 
						@ModelAttribute UserDTO userDto) throws BusinessException{
		
		logger.debug("UserController exe list?pager={}",pager);
		logger.debug("UserController exe list?UserDto={}",userDto);
		
		pager.setParams(userDto);
		userService.selectTList(pager);
		
		logger.debug("UserController exe list out={}",pager);
		
		return success(request,pager);
	}



}
