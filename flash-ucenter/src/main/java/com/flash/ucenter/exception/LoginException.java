package com.flash.ucenter.exception;

/**
 * 登录异常
 * 
 * @author Leon
 * @date 2015年2月9日
 * @since v 1.0
 */
public class LoginException extends UcenterException {

	private static final long serialVersionUID = -8766594985736519187L;

	public LoginException() {
		super();
	}

	public LoginException(int code,String message) {
		super(code, message);
	}
	
	LoginException(String message){
		super(message);
	}

}
