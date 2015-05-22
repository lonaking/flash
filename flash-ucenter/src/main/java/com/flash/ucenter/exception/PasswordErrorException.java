package com.flash.ucenter.exception;

/**
 * 密码错误异常
 * @author Leon
 * @date 2015年2月9日
 * @since v 1.0
 */
public class PasswordErrorException extends LoginException {
	
	private static final long serialVersionUID = 8965656038905751401L;

	public PasswordErrorException() {
		super();
	}
	public PasswordErrorException(String message){
		super(message);
	}
}
