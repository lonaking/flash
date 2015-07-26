package com.flash.ucenter.exception;

import com.flash.exception.annotation.Desc;
import com.flash.exception.resource.ExceptionCode;

/**
 * 所有Pte模块的业务异常码
 * @author lonaking
 */
public enum UcenterServiceExceptionCode implements ExceptionCode {
	@Desc(code=2003,msg = "未登录，请先登录")
	NOT_LOGIN_EXCEPTION,
	@Desc(code = 20021,msg="用户名不能为空")
	LOGIN_NAME_CAN_NOT_BE_NONE,
	@Desc(code = 20022, msg = "密码不能为空")
	PASSWORD_CAN_NOT_BE_NONE,
	@Desc(code = 2004, msg ="密码错误")
	PASSWORD_ERROR,
	@Desc(code = 2005, msg = "用户不存在")
	USER_IS_NOT_FOUND,
}
