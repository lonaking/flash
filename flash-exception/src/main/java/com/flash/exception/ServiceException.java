package com.flash.exception;

import com.flash.exception.resource.ExceptionCode;
import com.flash.exception.utils.ExceptionHelper;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	protected int errorCode;

	protected String errorMessage;
	
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public ServiceException(ExceptionCode code) {
		super(ExceptionHelper.getMessage(code));
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = this.getMessage();
		this.name = code.name();
	}

	public ServiceException(ExceptionCode code, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
		this.name = code.name();
	}

	public ServiceException(ExceptionCode code, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
		this.name = code.name();
	}

	public ServiceException(ExceptionCode code, String message) {
		super(message);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = ExceptionHelper.getMessage(code);
		this.name = code.name();
	}

	public ServiceException(ExceptionCode code, Throwable cause) {
		super(ExceptionHelper.getMessage(code), cause);
		this.errorCode = ExceptionHelper.getCode(code);
		this.errorMessage = this.getMessage();
		this.name = code.name();
	}

	public ServiceException(String msg) {
		super(msg);
	}

}
