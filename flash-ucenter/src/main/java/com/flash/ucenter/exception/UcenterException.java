package com.flash.ucenter.exception;

public class UcenterException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public UcenterException() {
		super();
	}

	UcenterException(String message){
		super(message);
	}
	
	public UcenterException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	public static final String NULLINPUTEXCEPTION_CODE = "2002";
	public static final String NULLLOGINNAMEEXCEPTION_CODE = "20021";
	public static final String NULLPASSWORDEXCEPTION_CODE = "20022";
	public static final String NOTLOGINEXCEPTION_CODE = "2003";
	public static final String PASSWORDERROREXCEPTION_CODE = "2004";
}
