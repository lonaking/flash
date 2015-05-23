package com.flash.base.web.response;


public class BaseResponse<T> {
	
	public BaseResponse() {
		super();
	}

	public BaseResponse( T data) {
		super();
		this.code = ResponseCode.CODE_OK;
		this.msg = ResponseMsg.MSG_SUCCESS;
		this.data = data;
	}
	
	public BaseResponse(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	// 响应业务状态
	private String code;
	// 响应消息
	private String msg;
	// 响应中的数据
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
