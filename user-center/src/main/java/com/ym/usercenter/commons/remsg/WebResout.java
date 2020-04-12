package com.ym.usercenter.commons.remsg;

import com.ym.usercenter.commons.enums.ConstantEnum;
import com.ym.usercenter.config.base.base.BaseObject;

import javax.servlet.http.HttpServletRequest;


/**
 * web响应参数
 * @author Administrator
 */
public class WebResout extends BaseObject {
	
	protected HttpServletRequest req = null;  //上下文
	
	protected long currentTimeMillis = 0; //系统时间
	protected String location = null;  //请求地址
	
	private int code = 0xffffffff;    //错误码
	private String desc = "";         //描述
	private String token = null;   //token
	private Object data = null;    //响应数据
	
	public WebResout() {
		this.currentTimeMillis = System.currentTimeMillis();
	}

	public WebResout(HttpServletRequest req) {
		this.currentTimeMillis = System.currentTimeMillis();
		this.location = req.getRequestURL().toString();
		this.req = req;
	}

	public WebResout(HttpServletRequest req,String token) {
		this.currentTimeMillis = System.currentTimeMillis();
		this.location = req.getRequestURL().toString();
		this.token = token;
		this.req = req;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		String outMessage = "";
		if(req != null) {
        		outMessage = ConstantEnum.getValue(desc);
		} else {
			//判断Key是否在枚举类型中
			if(ConstantEnum.contains(desc)) {
				outMessage = ConstantEnum.getValue(desc);
			} else {
				outMessage = desc;
			}
		}
		return outMessage;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getCurrentTimeMillis() {
		return currentTimeMillis;
	}
	public void setCurrentTimeMillis(long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
