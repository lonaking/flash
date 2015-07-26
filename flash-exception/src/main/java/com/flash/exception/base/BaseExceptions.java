package com.flash.exception.base;

import com.flash.exception.annotation.Desc;
import com.flash.exception.resource.ExceptionCode;


public enum BaseExceptions implements ExceptionCode {
	@Desc(msg = "未知异常",code=400)
	UNKNOWN_EXCEPTION,

	@Desc(msg = "用户认证错误:用户登录失败",code = 300)
	SESSION_VALIDATE_EXCEPTION
}
