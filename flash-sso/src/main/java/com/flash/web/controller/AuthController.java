package com.flash.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.web.response.BaseResponse;
import com.flash.commons.cookie.CookieUtils;
import com.flash.service.impl.AuthServiceImpl;
import com.flash.sso.token.Token;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.exception.LoginException;
import com.flash.ucenter.exception.UcenterException;
import com.flash.ucenter.exception.login.NotLoginException;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(AuthController.class);

	@Resource(name = "authService")
	private AuthServiceImpl authService;
	
	@RequestMapping(value="/ping/{name}",method = RequestMethod.GET)
	public @ResponseBody BaseResponse<?> ping(@PathVariable String name){
		User findUser = this.authService.findUser(name);
		BaseResponse<User> response = new BaseResponse<User>();
		response.setMsg("ping");
		response.setCode("200");
		response.setData(findUser);
		return response;
	}
	/**
	 * 登陆
	 * 
	 * @return
	 * @throws ResponseException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<String> login(HttpServletRequest request,
			HttpServletResponse response, AuthCommond authCommond) {
		//TODO 校验是否已经登录
		Token token = null;
		BaseResponse<String> result = null;
		try {
			token = this.authService.login(authCommond);
		} catch (LoginException e) {
			result = new BaseResponse<String>("400",e.getMessage(),"");
			return result;
		}
		CookieUtils.setCookie(request, response, "token", token.getTokenId(), 3600*24*100, "/");
		result = new BaseResponse<String>(token.getTokenId());
		return result;
	}
	
	/**
	 * 判断是否登录
	 * @author lonaking
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check",method = RequestMethod.GET)
	public @ResponseBody BaseResponse<String> checkLogin(HttpServletRequest request){
		String tokenId = CookieUtils.getCookie("token", request);
		Token token = null;
		try {
			token = this.authService.checkLogin(tokenId);
		} catch (UcenterException e) {
			BaseResponse<String> result = new BaseResponse<String>(e.getCode(),e.getMessage(),"");
			return result;
		}// null 没登录 !null 已经登录
		if(null != token){
			BaseResponse<String> result = new BaseResponse<String>(token.getTokenId());
			return result;
		}else{
			BaseResponse<String> result = new BaseResponse<String>("400", "未登录", "");
			return result;
		}
	}
	
	/**
	 * 退出登录
	 * @author lonaking
	 * @param request
	 * @return
	 * @throws UcenterException 
	 */
	@RequestMapping(value = "/out", method = RequestMethod.DELETE)
	public @ResponseBody BaseResponse<?> logout(HttpServletRequest request) throws UcenterException{
		String tokenId = CookieUtils.getCookie("token", request);
		try {
			Token token = this.authService.checkLogin(tokenId);
		} catch (UcenterException e) {
			BaseResponse<String> result = new BaseResponse<String>(e.getCode(),e.getMessage(),"");
			return result;
		}// null 没登录 !null 已经登录
		this.authService.logout(tokenId);
		BaseResponse<String> result = new BaseResponse<String>("200", "退出成功", "");
		return result;
	}
}
