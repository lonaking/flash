package com.flash.service;

import com.flash.sso.token.Token;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.exception.LoginException;
import com.flash.ucenter.exception.UcenterException;
import com.flash.ucenter.exception.login.NotLoginException;
import com.flash.web.controller.AuthCommond;

public interface AuthService {
	
	public Token login(AuthCommond user) throws LoginException;
	
	public boolean checkLogin(User user); 
	
	public Token checkLogin(String tokenId) throws UcenterException;
	
	public void logout(String tokenId) throws NotLoginException;
}
