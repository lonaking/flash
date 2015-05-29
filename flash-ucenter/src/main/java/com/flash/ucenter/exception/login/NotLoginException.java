package com.flash.ucenter.exception.login;

import com.flash.ucenter.exception.LoginException;

public class NotLoginException extends LoginException{
	private static final long serialVersionUID = -8409066332046078232L;
	
	
	public NotLoginException() {
		super();
	}
	
	public NotLoginException(String message){
		super(NOTLOGINEXCEPTION_CODE, message);
	}

	public NotLoginException(String code, String message) {
		super(code, message);
	}
}
