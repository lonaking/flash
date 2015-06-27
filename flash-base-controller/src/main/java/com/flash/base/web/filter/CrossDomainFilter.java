package com.flash.base.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 跨域请求过滤器
 * @author leon
 *
 */
public class CrossDomainFilter implements Filter{

	private static Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("CorossDomainFilter　init");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		response.addHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(req, resp);
		logger.info("CorossDomainFilter　请求通过");
	}

	@Override
	public void destroy() {
		
		
	}

}
