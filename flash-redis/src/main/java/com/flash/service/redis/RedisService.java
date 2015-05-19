package com.flash.service.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.flash.commons.json.JsonHelper;

/**
 * 一个公用的RedisService
 * 
 * @author lonaking
 */
public class RedisService {
	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
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
	public String set(final String key, final String value) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.set(key, value);
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
	public String setObj(String key, Object value){
		if(value.getClass().getTypeName().equals("String")){
			return set(key, (String) value);
		}
		String json = JsonHelper.transObjToJsonString(value);
		return set(key, json);
	}
	/**
	 * 将数据缓存到redis数据库并且设置过期时间
	 * 
	 * @return
	 */
	public String set(final String key, final String value, final Integer expire) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				String str = shardedJedis.set(key, value);
				shardedJedis.expire(key, expire);
				return str;
			}
		});
	}

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
	public Long expire(final String key, final Integer expire) {
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
	public String get(final String key) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				return shardedJedis.get(key);
			}
		});
	}
	
	public <T> T getObj(String key, Class<T> cla){
		String string = get(key);
		if(null == string)
			return null;
		return JsonHelper.transJsonStringToObj(string, cla);
	}

	public Long del(String key) {
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
	 * 从redis中取出一个list中的一个值
	 * 
	 * @author lonaking
	 * @param key
	 * @param field
	 * @return
	 */
	private String hget(final String key, final String field) {
		return this.execute(new Function<ShardedJedis, String>() {
			@Override
			public String execute(ShardedJedis shardedJedis) {
				String value = shardedJedis.hget(key, field);
				return value;
			}

		});
	}

	public <T> T hget(String key, String field, Class<T> cla) {
		String value = this.hget(key, field);
		if (StringUtils.isNotEmpty(value)) {
			return JsonHelper.transJsonStringToObj(value, cla);
		}
		return null;
	}

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
	 * 
	 * @author lonaking
	 * @param key
	 * @param field
	 * @param o
	 * @return
	 * @throws IOException 
	 */
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
	 * 批量将一个list
	 * @author lonaking
	 * @param key
	 * @param fields
	 * @param values
	 * @return
	 */
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
	public String hmsetObjMap(String key, Map<? extends Object, ? extends Object> objMap) {
		Map<String, String> map = new HashMap<String, String>();
		for (Entry<? extends Object, ? extends Object> entry : objMap.entrySet()) {
			map.put(entry.getKey().toString(), JsonHelper.transObjToJsonString(entry.getValue()));
		}
		return hmset(key, map);
	}
	
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
	
	public Long hdel(String key, String field) {
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
