package com.flash.ucenter.exception;


/**
 * 输入为空异常
 * @author Leon
 * @date 2015年2月10日
 * @since v 1.0
 */
public class NullInputException extends LoginException{
	
	private static final long serialVersionUID = -7653283506806342734L;

	public NullInputException(String message) {
		super(message);
	}
}
