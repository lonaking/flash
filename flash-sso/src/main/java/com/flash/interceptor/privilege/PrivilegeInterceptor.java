package com.flash.interceptor.privilege;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flash.base.web.response.BaseResponse;
import com.flash.commons.json.JsonHelper;
import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.privilege.annotation.PrivilegeAccess;
import com.flash.ucenter.privilege.token.Token;
import com.flash.ucenter.privilege.token.TokenHelper;

public class PrivilegeInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(PrivilegeInterceptor.class);

	@Resource(name = "tokenHelper")
	private TokenHelper tokenHelper;
	/**
	 * 判断用户是否有权限
	 * @param permission
	 * @return
	 */
	private boolean checkPermission(String permission){
		Token token = tokenHelper.getCurrentToken();
		if(null == token){
			return false;
		}
		List<Privilege> privileges = token.getPrivileges();
		for (Privilege privilege : privileges) {
			if(null != privilege && privilege.getSign().equals(permission)){
				logger.info("用户{}有权限{}，放行",token.getUser(),permission);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("拦截器前置方法");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		PrivilegeAccess privilegeAnnotation = method.getAnnotation(PrivilegeAccess.class);
		if(null == privilegeAnnotation){
			logger.debug("本API不需要权限，可直接运行");
			return true;
		}
		String sign = privilegeAnnotation.sign();//获取权限标识
		String stringToken = request.getHeader("access_token");
		if(null != stringToken && "" != stringToken)
			Thread.currentThread().setName(stringToken);//修改thread名为token值
		//检验是否有权限
		if(!checkPermission(sign)){
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(JsonHelper.transObjToJsonString(new BaseResponse<String>(400, "权限不足", null)));//这里有乱码
			out.close();
			return false;
		};
		logger.debug("stringToken获取到{}", stringToken);
		return true;
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
