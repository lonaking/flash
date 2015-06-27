package com.flash.service.redis.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.flash.commons.json.JsonHelper;
import com.flash.service.redis.Function;
import com.flash.service.redis.RedisService;

/**
 * 一个公用的RedisService,该类中所有的获取方法,若获取不到,均返回 null
 * 
 * @author lonaking
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService{
	private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	private <T> T execute(Function<ShardedJedis, T> fun) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return fun.execute(shardedJedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * 将数据缓存到redis数据库
	 * @return
	 */
	@Override
	public String set(final String key, final String value) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value);
			}

		});
	}

	/**
	 * 将数据缓存到redis数据库并且设置过期时间
	 * 
	 * @return
	 */
	@Override
	public String set(final String key, final String value, final Integer expire) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				String str = shardedJedis.set(key, value);
				logger.debug("存入redis成功，返回结果为{}",str);
				shardedJedis.expire(key, expire);
				return str;
			}
		});
	}

	/**
	 * 将一个对象存储进redis 支持List和Map
	 * @author lonaking
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public String setObj(String key, Object value){
		if(value.getClass().getSimpleName().equals("String")){
			return set(key, (String) value);
		}
		String json = JsonHelper.transObjToJsonString(value);
		return set(key, json);
	}
	
	/**
	 * 将一个对象序列化后存储到redis中
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public String setObj(final String key, final Object value, final Integer expire){
		String strValue = JsonHelper.transObjToJsonString(value);
		logger.debug("序列化key成功，序列化后的结果是{}",strValue);
		return set(key, strValue, expire);
	}

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
	@Override
	public long expire(final String key, final Integer expire) {
		return this.execute(new Function<ShardedJedis, Long>() {
			@Override
			public Long execute(ShardedJedis shardedJedis) {
				return shardedJedis.expire(key, expire);
			}
		});
	}

	/**
	 * 从redis缓存中读取数据
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public String get(final String key) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.get(key);
			}
		});
	}
	/**
	 * 获取一个存储在redis中的序列化的对象
	 * @param key
	 * @param cla
	 * @return 
	 * @return
	 */
	public <T> T getObj(String key, Class<T> cla){
		String string = get(key);
		if(null == string || "".equals(string)){
			return null;
		}else{
			T t = JsonHelper.transJsonStringToObj(string, cla);
			return t;
		}
	}
	@Override
	public long del(String key) {
		if (key == null)
			return 0L;
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		Long ret = 0L;
		try {
			ret = shardedJedis.del(key);
		} catch (Exception e) {
			
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return ret;
	}
	
	/**
	 * 批量删除
	 * @param keys
	 * @return
	 */
	@Override
	public long del(String[] keys) {
		if(null == keys || keys.length == 0){
			return 0L;
		}
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		Long ret = 0L;
		try {
			for (int i = 0; i < keys.length; i++) {
				del(keys[i]);
				ret = ret + 1;
			}
		} catch (Exception e) {
			
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return ret;
	}
	/**
	 * 从redis中取出一个list中的一个值
	 * 
	 * @author lonaking
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public String hget(final String key, final String field) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				String value = shardedJedis.hget(key, field);
				return value;
			}

		});
	}
	
	/**
	 * 获取一个存储在redis中的序列化的对象,该对象存储的方式是哈希
	 * @param key
	 * @param field
	 * @param cla
	 * @return
	 */
	@Override
	public <T> T hgetObj(String key, String field, Class<T> cla) {
		String value = this.hget(key, field);
		if (StringUtils.isNotEmpty(value)) {
			return JsonHelper.transJsonStringToObj(value, cla);
		}
		return null;
	}

	/**
	 * 将一条数据以哈希的结构存储进redis
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	@Override
	public long hset(String key, String field, String value) {
		ShardedJedis shardedJedis = null;
		Long v = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			v = shardedJedis.hset(key, field, value);
		} catch (Exception e) {

		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return v;
	}
	
	/**
	 * 
	 * @author lonaking
	 * @param key
	 * @param field
	 * @param o
	 * @return
	 * @throws IOException 
	 */
	@Override
	public long hset(String key, String field, Object o){
		String v = null;
		if (null == o) {
			v = "";
		} else {
			v = JsonHelper.transObjToJsonString(o);
		}
		return this.hset(key, field, v);
	}
	
	/**
	 * 获取存储在redis中的一个键为key的所有的值,将其保存在map中返回,返回字符串
	 * @param key
	 * @return
	 */
	@Override
	public Map<String, String> hgetAll(String key) {
		ShardedJedis shardedJedis = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			shardedJedis = shardedJedisPool.getResource();
			map = shardedJedis.hgetAll(key);
		} catch (Exception e) {
			
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return map;
	}
	
	/**
	 * 获取存储在redis中的一个键为key的所有的值,将其保存在map中返回,返回对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	@Override
	public <T> Map<String, T> hgetAll(String key, Class<T> clazz){
		Map<String, T> map = new HashMap<String, T>();
		if(StringUtils.isEmpty(key)){
			return null;
		}
		Map<String, String> stringAll = hgetAll(key);
		Set<Entry<String, String>> entrySet = stringAll.entrySet();
		for (Entry<String, String> entry : entrySet) {
			T t = JsonHelper.transJsonStringToObj(entry.getValue(), clazz);
			map.put(entry.getKey(), t);
		}
		return map;
	}

	/**
	 * 批量将一个list存储到redis
	 * @author lonaking
	 * @param key
	 * @param fields
	 * @param values
	 * @return
	 */
	@Override
	public <T> String hmset(String key, List<String> fields, List<T> values) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < fields.size(); i++) {
			map.put(fields.get(i), JsonHelper.transObjToJsonString(values.get(i)));
		}
		return hmset(key, map);
	}
	
	/**
	 * 将一个Map直接存入redis
	 * @author lonaking
	 * @param key
	 * @param objMap
	 * @return
	 */
	@Override
	public String hmsetObjMap(String key, Map<? extends Object, ? extends Object> objMap) {
		Map<String, String> map = new HashMap<String, String>();
		for (Entry<? extends Object, ? extends Object> entry : objMap.entrySet()) {
			map.put(entry.getKey().toString(), JsonHelper.transObjToJsonString(entry.getValue()));
		}
		return hmset(key, map);
	}
	
	@Override
	public String hmset(String key, Map<String, String> map) {
		if (map == null || map.size() == 0)
			return "OK";
		ShardedJedis shardedJedis = this.shardedJedisPool.getResource();
		String ret = null;
		try {
			ret = shardedJedis.hmset(key, map);
		} catch (Exception e) {
			
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return ret;
	}
	
	@Override
	public long hdel(String key, String field) {
		if (null == field || "".equals(field))
			return 0L;
		ShardedJedis shardedJedis = this.shardedJedisPool.getResource();
		Long ret = 0L;
		try {
			ret = shardedJedis.hdel(key, field);
		} catch (Exception e) {
			
		} finally {
			if (null != shardedJedis) {
				shardedJedis.close();
			}
		}
		return ret;
	}
}
