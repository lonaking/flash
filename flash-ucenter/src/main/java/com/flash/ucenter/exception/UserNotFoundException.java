package com.flash.ucenter.exception;

/**
 * 用户不存在异常
 * @author Leon
 * @date 2015年2月9日
 * @since v 1.0
 */
public class UserNotFoundException extends LoginException {

	private static final long serialVersionUID = 4481032942686785995L;

	private String userName;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public String getMessage() {
		return "用户" + userName + "不存在！";
	}
}
