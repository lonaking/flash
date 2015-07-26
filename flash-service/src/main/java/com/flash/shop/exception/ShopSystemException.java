package com.flash.shop.exception;

import com.flash.exception.SystemException;

/**
 * ucenter模块系统异常，无需抛出
 * @author lonaking
 */
public class ShopSystemException extends SystemException {

	private static final long serialVersionUID = 1L;

	public ShopSystemException(ShopServiceExceptionCode code, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}

	public ShopSystemException(ShopServiceExceptionCode code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public ShopSystemException(ShopServiceExceptionCode code, String message) {
		super(code, message);
	}

	public ShopSystemException(ShopServiceExceptionCode code, Throwable cause) {
		super(code, cause);
	}

	public ShopSystemException(ShopServiceExceptionCode code) {
		super(code);
	}

}
