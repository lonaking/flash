package com.flash.ucenter.exception;

import com.flash.exception.ServiceException;
import com.flash.exception.resource.ExceptionCode;

/**
 * ucenter 模块业务异常 ucenter模块所有的interface都应该抛出此异常
 * @author lonaking
 */
public class UcenterServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public UcenterServiceException(ExceptionCode code, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}

	public UcenterServiceException(ExceptionCode code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public UcenterServiceException(ExceptionCode code, String message) {
		super(code, message);
	}

	public UcenterServiceException(ExceptionCode code, Throwable cause) {
		super(code, cause);
	}

	public UcenterServiceException(ExceptionCode code) {
		super(code);
	}

}
