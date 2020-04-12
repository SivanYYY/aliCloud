package com.ym.usercenter.commons.enums;

public enum ConstantEnum {
	
	//全局操作成功失败
	_SUCCESS("system.success.msg","成功", 0),
	_FAIL("system.fail.msg","失败", -1),

	//系统层面代码异常
	_fial_exp("system.server.exp","服务器异常",500),
	
	_TOKEN_FAIL_NULL("token.fail.null","TOKEN不能为空",-100),
	_TOKEN_FAIL_IS_ERROR("token.fail.is.error","TOKEN错误",-100),
	_TOKEN_FAIL_KEY_VAL("token.fail.key.val","TOKEN保存的值错误",-100),
    _TOKEN_OUT_TIME_VAL("token.out.time.val","TOKEN过期",-100),
	
	_WEB_NOT_LOGIN("web.not.login","Boss系统未登录",-1),
	_WEB_NOT_LOGIN_ERROR("web.not.login.error","登录错误",-1),
	
	_WEB_NOT_AUTH_ERROR("web.not.auth.error","没有权限",300),
	_WEB_DTAT_IS_NULL("web.data.is.null","数据不存在或已被删除",-1),
	
	//dao exp 
	DAO_ERROR_EXP("dao.error.exp","数据操作失败",-1),
	
	//dev 开发错误 100
	DEV_PARM_ERROR("dev.pram.error","缺少必要参数错误",-9999),
	DEV_PARM_TYPE_ERROR("dev.pram.type.error","参数类型错误",-9999),
	
	//账户不存在
	APP_SER_MEMBER_NOT_FUND("app.ser.member.not.fund","账户不存在!",-300),
	
	//web server des 错误码从2000 开始
	WEB_SER_controller_exp("web.ser.controller.exp","业务访问出现异常",-1),
	WEB_SER_paypass_exp("web.ser.paypass.exp","支付密码错误",-3000),

	app_update_info("app.update.info","请升级当前版本",-9998),
	handler_exp("handler.exp","缺少头信息",-9997),
	
	
	i18n_not_exp("i18n.not.exp","国际化语言错误",-10000),
	
	/////////////////////server msg  title/////////////////////////
	
	
	//api auth api handler
	to_auth_member_idcard("auth.member.idcard.to","接收方未完成实名认证,请确认",-1),
	auth_member_idcard("auth.member.idcard","尚未完成实名认证，请认证",-1),
	auth_member_payPass("auth.member.paypass","请设置支付密码",-1),
	auth_member_payPass_error("auth.member.paypass.error","支付密码错误",-1),


	// 登录注册
	member_check_code_error("member.validation_code.error","验证码错误",-1),
	member_inconsistent_pass_error("member.inconsistent_password.error","两次输入密码不一致",-1),
	member_isuser_error("member.isuser.error","该账户已经注册过了",-1),
	member_invite_code_error("member.invite_code.error","邀请码不正确",-1),

	//api check order
	walleat_order_check_orderno("walleat.order.check.orderno","订单不存在",-1),

	//撤单
	walleat_order_reback_success("walleat.order.reback.success","撤单成功",1),
	walleat_order_reback_fail("walleat.order.reback.fail","撤单失败",-1),
	
	//买单
	walleat_order_buypush_success("walleat.order.buy.push.success","购买成功",1),
	walleat_order_buypush_fail("walleat.order.buy.push.fail","撤单失败",-1),
	
	walleat_coin_money_no_account("walleat.coin.money.no.account","你还没有资产账户",-1),
	walleat_coin_money_account_insufficient("walleat.coin.money.insufficient","资产账户余额不足",-1),
	
	push_money_insufficient("push.money.insufficient","锁仓余额不足",-1),
	sys_push_money_insufficient("push.sys.money.insufficient","锁仓余额不足",-1),


	// 活动质押
	help_success("help_success","助力成功",998),
		
	/////////////////////server msg  title//////////////////////////
	myTest("user.title","默认中文提示",-1);




	private String i18nKey;  //国际码
	private String desc; //默认描述
	private int val; //错误码

	private ConstantEnum(String i18nKey,String desc, int val) {
		this.i18nKey = i18nKey;
		this.desc = desc;
		this.val = val;
	}
	public int getVal() {
		return this.val;
	}
	public String getDesc() {
		return this.desc;
	}

	public String getI18nKey() {
		return i18nKey;
	}
	
	public static boolean contains(String key){
		boolean inContains = false;
		for(ConstantEnum i18nKey : ConstantEnum.values()) {
			if(i18nKey.getI18nKey().equals(key)) {
				inContains = true;
				break;
			}
		}
		return inContains;
	}
	
	public static String getValue(String key){
		String value = "";
		for(ConstantEnum i18nKey : ConstantEnum.values()) {
			if(i18nKey.getI18nKey().equals(key)) {
				value = i18nKey.getDesc();
				break;
			}
		}
		return value;
	}
}
