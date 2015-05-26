package com.flash.ucenter.dao.shiro;

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
import org.springframework.stereotype.Repository;

import com.flash.ucenter.dao.UserDao;
import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.domain.Role;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.service.UserService;
@Component
@Repository("defaultRealm")
public class SampleRealm extends AuthorizingRealm {

	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name="userService")
	private UserService userService;

	/**
	 * 获取身份相关信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
		User user = this.userDao.findEntityById(userId);
		if(user != null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Role role = user.getRole();
			info.addRole(role.getName());
			Set<Privilege> privileges = role.getPrivileges();
			for (Privilege privilege : privileges) {
				
				//权限一条一条的添加
				/*Set<Order> orders = user.getOrders();
				if(orders != null && orders.size()!=0){
					List<String> permissions = new ArrayList<String>();
					for (Order order : orders) {
						permissions.add(Order.class.getName().toUpperCase()+":"+"");
					}
				}
				*/
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
		User user = this.userService.findUser(token.getUsername());
		if( user != null ) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        } else {
            return null;
        }
	}

}
