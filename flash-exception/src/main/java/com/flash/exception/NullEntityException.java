package com.flash.exception;

import com.flash.exception.base.BaseException;

public class NullEntityException extends BaseException{
	private static final long serialVersionUID = 1L;
	
	public NullEntityException(Integer code, String msg) {
		super(code, msg);
	}
	
	public NullEntityException(String msg){
		super(msg);
	}
}
