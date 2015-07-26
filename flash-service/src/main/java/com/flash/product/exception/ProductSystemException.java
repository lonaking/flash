package com.flash.product.exception;

import com.flash.exception.SystemException;

/**
 * ucenter模块系统异常，无需抛出
 * @author lonaking
 */
public class ProductSystemException extends SystemException {

	private static final long serialVersionUID = 1L;

	public ProductSystemException(ProductServiceExceptionCode code, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}

	public ProductSystemException(ProductServiceExceptionCode code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public ProductSystemException(ProductServiceExceptionCode code, String message) {
		super(code, message);
	}

	public ProductSystemException(ProductServiceExceptionCode code, Throwable cause) {
		super(code, cause);
	}

	public ProductSystemException(ProductServiceExceptionCode code) {
		super(code);
	}

}
