package com.ym.usercenter.config.base.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseObject {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String language; //国际化参数
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
