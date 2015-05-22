package com.flash.ucenter.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.ucenter.dao.UserDao;
import com.flash.ucenter.domain.User;

@Repository("userDao")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

	@Override
	public User findUser(User user) {
		List<User> list = this.hibernateTemplate.find(
				"from User where loginName=? and password=?",
				user.getLoginName(), user.getPassword());
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User findUserByLoginName(String name) {
		String query = "from User where loginName=?";
		List<User> list = this.hibernateTemplate.find(query, name);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
}
