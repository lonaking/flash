package com.flash.ucenter.privilege.token;

import java.util.List;

import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.domain.User;

/**
 * 令牌
 * 
 * @author lonaking
 */
public class Token {

	private String tokenId;
	private User user;
	private List<Privilege> privileges;
	private long expireTime;
	private long createTime;
	private long updateTime;

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", user=" + user + ", privileges="
				+ privileges + ", expireTime=" + expireTime + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

	public Token() {
		super();
	}

	public Token(String tokenId, User user, List<Privilege> privileges) {
		super();
		this.tokenId = tokenId;
		this.user = user;
		this.privileges = privileges;
		this.expireTime = 30 * 60;
		this.createTime = System.currentTimeMillis();
		this.updateTime = System.currentTimeMillis();
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

}
