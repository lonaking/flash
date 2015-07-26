package com.flash.web.framework;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.web.response.BaseResponse;
import com.flash.exception.ServiceException;

@Controller
public class WebApiBaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebApiBaseController.class);
	
	/**
	 * 普通业务异常的处理器
	 * @author lonaking
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public @ResponseBody BaseResponse<?>handleServiceException(HttpServletRequest request,
			ServiceException e){
		String name = e.getName() ;
		String message = (e.getErrorMessage() == null || "".equals(e.getErrorMessage().trim())) ? "未知业务异常" : e.getErrorMessage();
		Integer code = e.getErrorCode() == 0 ? 400 : e.getErrorCode();
		LOGGER.error("运行中出现业务异常,原因:{},异常码{},异常名称{}",message,code,name);
		return BaseResponse.faild(code, message, name);
	}
}
