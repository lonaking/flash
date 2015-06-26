package com.flash.base.web.privilege.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flash.base.web.privilege.annotation.PrivilegeAccess;
import com.flash.base.web.response.BaseResponse;
import com.flash.commons.json.JsonHelper;

public class PrivilegeInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(PrivilegeInterceptor.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("拦截器前置方法");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		PrivilegeAccess privilegeAnnotation = method.getAnnotation(PrivilegeAccess.class);
		if(null == privilegeAnnotation){
			String string = UUID.randomUUID().toString();
			Thread.currentThread().setName(string);
			logger.debug("stringToken获取到{}", string);
			return true;
		}
		String sign = privilegeAnnotation.sign();
		String stringToken = request.getHeader("access_token");
		Thread.currentThread().setName(stringToken);
		logger.debug("stringToken获取到{}", stringToken);
		//request.setAttribute(name, o);
		//从redis中取出来权限判断
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(JsonHelper.transObjToJsonString(new BaseResponse<String>(400, "权限不足", null)));//这里有乱码
		out.flush();
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("拦截器后置方法");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("拦截器完成方法");
		
	}

}
