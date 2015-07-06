package com.flash.exception.base;

public class BaseException extends Exception {
	private static final long serialVersionUID = 3003916867081183021L;

	private Integer code;

	private String msg;
	public BaseException() {
		super();
	}

	public BaseException(String msg) {
		super(msg);
		this.code = 200;
		this.msg = msg;
	}

	public BaseException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
