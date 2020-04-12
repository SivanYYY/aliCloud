package com.ym.usercenter.config;

import javax.servlet.http.HttpServletRequest;

import com.ym.usercenter.config.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

//
public class ApiBaseController extends BaseController {

	public static final String TEXT_UTF8 = "text/html;charset=UTF-8";
	public static final String JSON_UTF8 = "application/json;charset=UTF-8";
	public static final String XML_UTF8 = "application/xml;charset=UTF-8";

	public static final String LIST = "list";
	public static final String VIEW = "view";
	public static final String ADD = "add";
	public static final String SAVE = "save";
	public static final String EDIT = "edit";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String PAGE = "page";

//	@Autowired
//	protected JwtUtils jwtUtils;
//	@Autowired
//	protected IWalleatMemberService walleatMemberService;
//
//	public WalleatMember getMember(HttpServletRequest request) {
//		String token = "";
//    	String tokenHeader = request.getHeader("token");
//    	String tokenParam = request.getParameter("token");
//    	if(!StringUtils.isEmpty(tokenParam)) token = tokenParam;
//    	if(!StringUtils.isEmpty(tokenHeader)) token = tokenHeader;
//
//    	//token 为空
//    	if(StringUtils.isEmpty(token)) {
//    		logger.info("AuthenticationInterceptor.preHandle  token isnull");
//    		throw new TokenException(ConstantEnum._TOKEN_FAIL_NULL.getVal()
//    				, ConstantEnum._TOKEN_FAIL_NULL.getI18nKey());
//    	}
//
//    	 // 获取 token 中的 member id loginAccount
//        String memberId = null;
//        String loginAccount = null;
//        try {
//        	memberId = JWT.decode(token).getAudience().get(0);
//            loginAccount = JWT.decode(token).getAudience().get(1);
//        } catch (JWTDecodeException e) {
//            logger.info("AuthenticationInterceptor.preHandle  token error");
//            throw new TokenException(ConstantEnum._TOKEN_FAIL_IS_ERROR.getVal(),
//            		ConstantEnum._TOKEN_FAIL_IS_ERROR.getI18nKey());
//        }
//        //token 过期
//        if(!jwtUtils.containsToken(loginAccount)) {
//            logger.info("AuthenticationInterceptor.preHandle  token error");
//            throw new TokenException(ConstantEnum._TOKEN_OUT_TIME_VAL.getVal(),
//            		ConstantEnum._TOKEN_OUT_TIME_VAL.getI18nKey());
//        }
//
//        //验证会员信息
//        if (StringUtils.isEmpty(memberId)) {
//            logger.info("AuthenticationInterceptor.preHandle  token isnull");
//            throw new TokenException(ConstantEnum._TOKEN_FAIL_IS_ERROR.getVal(),
//            		ConstantEnum._TOKEN_FAIL_IS_ERROR.getI18nKey());
//        }
//		return walleatMemberService.selectById(Integer.valueOf(memberId));
//	}
}
