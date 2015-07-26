package com.flash.base.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flash.base.web.response.BaseResponse;
import com.flash.commons.json.JsonHelper;
import com.flash.exception.ServiceException;
import com.flash.exception.utils.ExceptionHelper;

public class ExceptionFilter implements Filter{

	private static Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("ExceptionFilter init success!");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try{
			chain.doFilter(httpServletRequest, httpServletResponse);
		}catch(Exception be){
			BaseResponse<String> result = new BaseResponse<String>();
			int code = 0;
			String message = null;
			if((be.getClass().getName()).equals(ServiceException.class.getName())){
				ServiceException serviceException = (ServiceException) be;
				code = serviceException.getErrorCode();
				message = serviceException.getErrorMessage();
				logger.debug("运行中出现业务异常{},原因{}",serviceException.getName(),serviceException.getErrorMessage());
			}else{
				code = ExceptionHelper.DEFAULT_EXCEPTION_CODE;
				message = be.getMessage().substring(((be.getMessage().indexOf(":"))+1));
				logger.debug("运行中出现未知业务异常{}", be.getCause());
			}
			result.setCode(code);
			result.setMsg(message.trim());
			PrintWriter out = response.getWriter();
			httpServletResponse.setContentType("application/json");
			httpServletResponse.setCharacterEncoding("UTF-8");
			String resp = JsonHelper.transObjToJsonString(result);
			logger.debug("运行中出现非业务异常{}",resp);
			out.write(resp);//这里有乱码
			out.close();
			return ;
		}
	}

	@Override
	public void destroy() {
		logger.debug("ExceptionFilter destroy success!");
	}
}
