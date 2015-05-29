package com.flash.ucenter.exception;

import com.flash.ucenter.exception.login.NullInputOptions;


/**
 * 输入为空异常
 * @author Leon
 * @date 2015年2月10日
 * @since v 1.0
 */
public class NullInputException extends LoginException{
	
	private static final long serialVersionUID = -7653283506806342734L;

	public NullInputException(NullInputOptions option,String message) {
		super(message);
		if(option.equals(NullInputOptions.COMMON)){
			super.setCode(NULLINPUTEXCEPTION_CODE);
			return ;
		}
		if(option.equals(NullInputOptions.LOGINNAME)){
			super.setCode(NULLLOGINNAMEEXCEPTION_CODE);
			return ;
		}
		if(option.equals(NullInputOptions.PASSWORD)){
			super.setCode(NULLPASSWORDEXCEPTION_CODE);
			return ;
		}
	}
	
	
}
