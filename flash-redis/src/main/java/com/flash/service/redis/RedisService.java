package com.flash.service.redis;

import java.util.List;
import java.util.Map;

/**
 * redisService
 * @author Leon
 * @date 2015年2月2日
 * @since v 1.0
 */
public interface RedisService {

	public String set(String key, String value);

	public String set(String key, String value, Integer expire);
	
	public String setObj(String key, Object value);
	
	public String setObj(String key, Object value, Integer expire);

	public long expire(String key, Integer expire);
	
	public String get(String key);
	
	public <T> T getObj(String key,Class<T> cla);
	
	public long del(String key);
	
	public long del(String[] keys);
	
	public long hset(String key, String field, String value);
	
	public long hset(String key, String field, Object o);

	public String hget(final String key, final String field);
	
	public <T> T hgetObj(String key, String field, Class<T> cla);
	
	public Map<String, String> hgetAll(String key);
	
	public <T> Map<String, T> hgetAll(String key, Class<T> clazz);
	
	public <T> String hmset(String key, List<String> fields, List<T> values);
	
	public String hmset(String key, Map<String, String> map);
	
	public String hmsetObjMap(String key, Map<? extends Object, ? extends Object> objMap);
	
	public long hdel(String key, String field);
	
}
