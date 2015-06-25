package com.flash.ucenter.exception;

public class UcenterException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int code;
	
	public UcenterException() {
		super();
	}

	UcenterException(String message){
		super(message);
	}
	
	public UcenterException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
	
	public static final int NULLINPUTEXCEPTION_CODE = 2002;
	public static final int NULLLOGINNAMEEXCEPTION_CODE = 20021;
	public static final int NULLPASSWORDEXCEPTION_CODE = 20022;
	public static final int NOTLOGINEXCEPTION_CODE = 2003;
	public static final int PASSWORDERROREXCEPTION_CODE = 2004;
}
