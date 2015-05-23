package com.flash.service;

import com.flash.ucenter.domain.User;

public interface AuthService {
	
	public User login(User user);
	
	public boolean checkLogin(User user); 
}
