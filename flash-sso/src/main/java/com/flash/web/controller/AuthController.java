package com.flash.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.commons.cookie.CookieUtils;
import com.flash.domain.User;
import com.flash.result.BaseResponse;
import com.flash.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthController.class);

	@Resource(name = "authService")
	private AuthService authService;
	/**
	 * 登陆
	 * 
	 * @return
	 * @throws ResponseException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<?> login(HttpServletRequest request,
			HttpServletResponse response, AuthCommond authCommond) {
		User user = new User();
		BeanUtils.copyProperties(user, authCommond);
		this.authService.login(user);
		CookieUtils.setCookie(request, response, "_user", user.getId()+"", 3600*24*100, "/");
		return null;
	}
}
