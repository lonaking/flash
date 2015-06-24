package com.flash.exception.resource;

import com.flash.exception.annotation.ExceptionMsg;

public class ExceptionCode {
	@ExceptionMsg(msg = "输入为空")
	public static final String NULLINPUTEXCEPTION_CODE = "2002";
	
	@ExceptionMsg(msg = "登录名不能为空")
	public static final String NULLLOGINNAMEEXCEPTION_CODE = "20021";
	
	@ExceptionMsg(msg = "密码不能为空")
	public static final String NULLPASSWORDEXCEPTION_CODE = "20022";
	
	@ExceptionMsg(msg = "未登录，请先登录")
	public static final String NOTLOGINEXCEPTION_CODE = "2003";
	
	@ExceptionMsg(msg = "密码错误")
	public static final String PASSWORDERROREXCEPTION_CODE = "2004";
}
