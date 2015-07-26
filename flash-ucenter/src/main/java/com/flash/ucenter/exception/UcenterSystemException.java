package com.flash.ucenter.exception;

import com.flash.exception.SystemException;

/**
 * ucenter模块系统异常，无需抛出
 * @author lonaking
 */
public class UcenterSystemException extends SystemException {

	private static final long serialVersionUID = 1L;

	public UcenterSystemException(UcenterServiceExceptionCode code, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}

	public UcenterSystemException(UcenterServiceExceptionCode code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public UcenterSystemException(UcenterServiceExceptionCode code, String message) {
		super(code, message);
	}

	public UcenterSystemException(UcenterServiceExceptionCode code, Throwable cause) {
		super(code, cause);
	}

	public UcenterSystemException(UcenterServiceExceptionCode code) {
		super(code);
	}

}
