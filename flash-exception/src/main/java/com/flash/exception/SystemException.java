package com.flash.exception;

import com.flash.exception.resource.ExceptionCode;
import com.flash.exception.utils.ExceptionHelper;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected int errorCode;

	protected String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public SystemException(ExceptionCode code) {
		super(ExceptionHelper.getMessage(code));
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = this.getMessage();
	}

	public SystemException(ExceptionCode code, String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
	}

	public SystemException(ExceptionCode code, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
	}

	public SystemException(ExceptionCode code, String message) {
		super(message);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
	}

	public SystemException(ExceptionCode code, Throwable cause) {
		super(ExceptionHelper.getMessage(code), cause);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = this.getMessage();
	}

}
