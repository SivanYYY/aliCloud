package com.ym.usercenter.commons.exceptions;

public class VersionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected int expCode;
	protected String expMsg;

	public VersionException(int expCode, String expMsg) {
		this.expCode = expCode;
		this.expMsg = expMsg;
	}

	public int getExpCode() {
		return expCode;
	}

	public void setExpCode(int expCode) {
		this.expCode = expCode;
	}

	public String getExpMsg() {
		return expMsg;
	}

	public void setExpMsg(String expMsg) {
		this.expMsg = expMsg;
	}

	@Override
	public String toString() {
		return "BizException [expCode=" + expCode + ", expMsg=" + expMsg + "]";
	}
}

