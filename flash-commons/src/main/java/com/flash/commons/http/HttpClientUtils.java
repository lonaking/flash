package com.flash.commons.http;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
	
	/**
	 * Https get请求
	 * @author lonaking
	 * @return 
	 */
	public static String httpsGet(String url){
		SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		try {
			sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
			//构造https请求的client
			CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			//TODO  这里没用做response.close() 后期更换为httpclient 连接池子
			LOGGER.debug("httpsget请求发送成功，得到数据{}", result);
			return result;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		} catch (KeyStoreException e) {
			LOGGER.error(e.getMessage());
		} catch (ClientProtocolException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (KeyManagementException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
