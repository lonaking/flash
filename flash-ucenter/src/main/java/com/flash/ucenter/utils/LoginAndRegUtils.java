package com.flash.ucenter.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 登录注册工具类
 * 
 * @author Leon
 * @date 2014年12月24日
 * @since v 1.0
 */
public class LoginAndRegUtils {
	/**
	 * 使用md5的算法进行加密
	 * 
	 * @param plainText
	 *            加密原文
	 * @return 加密密文
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		return new BigInteger(1, secretBytes).toString(16);
	}
	/**
	 * 生成随机的4位数字
	 * @return
	 */
	public static String makePasswordKey(){
		String key = UUID.randomUUID().toString().substring(0, 4);
		return key;
	}
	
	
}
