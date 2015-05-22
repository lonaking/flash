package com.flash.ucenter.exception;

/**
 * 验证码异常
 * @author Leon
 * @date 2015年2月9日
 * @since v 1.0
 */
public class CheckCodeException extends LoginException {

	private static final long serialVersionUID = 4481032942686785995L;

	public CheckCodeException() {
		super();
	}
	
	public CheckCodeException(String str) {
		super();
	}

	@Override
	public String getMessage() {
		return "验证码错误";
	}
}
