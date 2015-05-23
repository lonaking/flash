package com.flash.shiro.realm;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.flash.dao.AuthDao;
import com.flash.service.impl.AuthServiceImpl;
import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.domain.Role;
import com.flash.ucenter.domain.User;
@Component
public class DefaultRealm extends AuthorizingRealm {

	@Resource(name = "authDao")
	private AuthDao authDao;
	
	@Resource(name="authService")
	private AuthServiceImpl authService;

	/**
	 * 获取身份相关信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
		User user = this.authDao.findEntityById(userId);
		if(user != null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Role role = user.getRole();
			info.addRole(role.getName());
			Set<Privilege> privileges = role.getPrivileges();
			for (Privilege privilege : privileges) {
//				System.out.println(role.getId()+"===="+privilege.getSign());
				//管理员添加店铺，这里的格式如:shop:add(粗粒度的权限控制，允许)
				//编辑id为5的店铺，格式如：shop:edit:5(细粒度的权限控制，到对象)
				//允许编辑所有的店铺 shop:edit
				info.addStringPermission(privilege.getSign());
			}
			return info;
		}
		return null;
	}

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = this.authService.findUser(token.getUsername());
		if( user != null ) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        } else {
            return null;
        }
	}

}
