package com.flash.commons.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookie帮助类
 * 
 * @author Leon
 * @date 2015年2月10日
 * @since v 1.0
 */
public class CookieUtils {

	protected static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);
	
	public static final String ENCODE_UTF8 = "utf-8";
	
	public static final int COOKIE_AGE_DAY = 3600 * 24;
	
	public static final int COOKIE_AGE_WEEK = 3600 * 24 * 7;
	
	public static final int COOKIE_AGE_MONTH = 3600 * 24 * 30;
	
	public static final int COOKIE_AGE_YEAR = 3600 * 24 * 365;

	/**
	 * 设置Cookie,不设置有效时间,浏览器关闭即失效,也不编码,也不设置路径(默认为"/")
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue){
		setCookie(request, response, cookieName, cookieValue, -1);
	}
	
	/**
	 * 设置cookie 设置有效期，但不编码,也不设置路径(默认为"/")
	 * 
	 * @param cookieName cookie名
	 * @param cookieValue cookie值
	 * @param age cookie有效期（生命周期）
	 * @param path cookie有效路径
	 * @param response HttpServletResponse对象
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int age) {
		setCookie(request, response, cookieName, cookieValue, age, "/");
	}
	/**
	 * 设置cookie 设置有效期，但不编码,设置路径
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param age
	 * @param path
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int age, String path) {
		setCookie(request, response, cookieName, cookieValue, age, path, false);
	}
	/**
	 * 设置cookie 设置有效期，编码(默认utf-8),设置路径
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param age
	 * @param path
	 * @param isEncode
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int age, String path ,boolean isEncode) {
		doSetCookie(request, response, cookieName, cookieValue, age, path, isEncode, null);
	}
	/**
	 * 设置Cookie 
	 * @param cookieName
	 * @param cookieValue
	 * @param age
	 * @param path
	 * @param isEncode
	 * @param encode
	 * @param response
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int age, String path ,boolean isEncode ,String encode) {
		doSetCookie(request, response, cookieName, cookieValue, age, path, isEncode, encode);
	}
	
	/**
	 * 设置Cookie的方法
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param age
	 * @param path
	 * @param isEncode
	 * @param encode
	 */
	private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int age, String path, boolean isEncode, String encode){
		try {
			if(cookieValue == null){
				cookieValue = "";
			}else{
				if (isEncode){
					if(encode == null || encode == "")
						encode = ENCODE_UTF8;
					cookieValue = URLEncoder.encode(cookieValue, encode);
				}
			}
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if(age > 0){
				cookie.setMaxAge(age);
			}
//			if(null != request){
//				cookie.setDomain(getDomainName(request));
//			}
			cookie.setPath(path);
			response.addCookie(cookie);
			
		} catch (UnsupportedEncodingException e) {
			logger.error("Cookie Encode Error.", e);
		}
		
		
	}

	/**
	 * 得到Cookie的域名
	 * @param request
	 * @return
	 */
	private static String getDomainName(HttpServletRequest request) {
		String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
	}
	
	public static void removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName) ){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * 获取cookie 不编码
	 * 
	 * @param string cookie名
	 * @return URLDecoder完成的cookie
	 */
	public static String getCookie(String cookieName, HttpServletRequest request) {
		return getCookie(cookieName, request, false);
	}

	/**
	 * 获取cookie 
	 * 
	 * @param cookieName
	 * @param request
	 * @param isDecoder 是否编码
	 * @return
	 */
	public static String getCookie(String cookieName, HttpServletRequest request, boolean isDecoder) {
		return getCookie(cookieName, request, ENCODE_UTF8, isDecoder);
	}

	/**
	 * 获取cookie，根据指定编码解码
	 * 
	 * @param cookieName
	 * @param request
	 * @param encode
	 * @return
	 */
	public static String getCookie(String cookieName, HttpServletRequest request, String encode, boolean isDecoder) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0 || cookieName == null) {
			return null;
		}
		String cookieValue = null;
		try {
			for (Cookie coo : cookies) {
				if (coo.getName().equals(cookieName)) {
					if (coo.getValue() != null || coo.getValue() != "") {
						if (isDecoder) {
							cookieValue = URLDecoder.decode(coo.getValue(), encode);
						} else {
							cookieValue = coo.getValue();
						}
						break;
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("Cookie Decode Error.", e);
		}
		return cookieValue;
	}

}