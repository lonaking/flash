package com.flash.exception.base;

public class BaseException extends Exception {
	private static final long serialVersionUID = 3003916867081183021L;

	private String code;
	private String msg;

	public BaseException() {
		super();
	}

	public BaseException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
