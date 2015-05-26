package com.flash.base.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.service.redis.RedisService;

public class RedisAndDbDaoImpl<T> extends CommonDaoImpl<T>{

	private static Logger logger = LoggerFactory.getLogger(RedisAndDbDaoImpl.class);
	
	@Resource(name = "redisService")
	private RedisService redisService;
	
	@Override
	public void saveEntity(T t) {
		String key = t.getClass().getName();
		String id = super.getClassMetadata().getIdentifierPropertyName();
		try{
			super.saveEntity(t);
			logger.info("数据成功存储进数据库,key为{},主键为{}",key,id);
		}catch(Exception e){
			logger.info("数据存储进数据库失败,key为{},主键为{}",key,id);
		}
		try{
			this.redisService.setObj(key, t);
			logger.info("数据成功存储进redis,key为{},主键为{}",key,id);
		}catch(Exception e){
			logger.info("数据存储进redis失败,key为{},主键为{}",key,id);
		}
	}

	@Override
	public void updateEntity(T t) {
		String key = t.getClass().getName();
		String id = super.getClassMetadata().getIdentifierPropertyName();
		try{
			super.updateEntity(t);
			logger.info("success to udpate data in db,key is {},identify is {}",key,id);
		}catch(Exception e){
			logger.info("failed to udpate data in db,key is {},identify is {}",key,id);
		}
		try{
			this.redisService.setObj(key, t);
			logger.info("success to udpate data in redis,key is {},identify is {}",key,id);
		}catch(Exception e){
			logger.info("failed to udpate data in redis,key is {},identify is {}",key,id);
		}
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		String key = t.getClass().getName();
		String id = super.getClassMetadata().getIdentifierPropertyName();
		try{
			super.saveOrUpdateEntity(t);
			logger.info("success to save or udpate data in db,key is {},identify is {}",key,id);
		}catch(Exception e){
			logger.info("failed to save or udpate data in db,key is {},identify is {}",key,id);
		}
		try{
			this.redisService.setObj(key, t);
			logger.info("success to save or udpate data in redis,key is {},identify is {}",key,id);
		}catch(Exception e){
			logger.info("failed to save or udpate data in redis,key is {},identify is {}",key,id);
		}
	}

	@Override
	public void deleteEntityById(Serializable id) {
		String key = super.getCla().getName();
		try{
			super.deleteEntityById(id);
			logger.info("success to delete data from db,key is {},identify is {}",key,id);
		}catch(Exception e){
			logger.info("failed to delete data from db,key is {},identify is {}",key,id);
		}
		try{
			long del = this.redisService.del(id.toString());
			if(del == 0){
				logger.info("failed to del data from redis,becaulse the data is not in redis,key is {},identify is {}",key,id);
			}else{
				logger.info("success to del data from redis,key is {},identify is {}",key,id);
			}
		}catch(Exception e){
			logger.info("failed to del data from redis,key is {},identify is {}",key,id);
		}
	}

	@Override
	public void deleteEntityByIds(Serializable[] ids) {
		if(null == ids || ids.length == 0){
			return ;
		}
		String key = super.getCla().getName();
		
		String[] keys = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			String serializable = ids[i].toString();
			keys[i] = serializable;
		}
		try{
			super.deleteEntityByIds(ids);
			logger.info("success to delete data from db,key is {},identify is {}",key,ids.toString());
		}catch(Exception e){
			logger.info("failed to delete data from db,key is {},identify is {}",key,ids.toString());
		}
		try{
			long del = this.redisService.del(keys);
			if(del == 0){
				logger.info("failed to del data from redis,becaulse the data is not in redis,key is {},identify is {}",key,ids.toString());
			}else{
				logger.info("success to del data from redis,key is {},identify is {}",key,ids.toString());
			}
		}catch(Exception e){
			logger.info("failed to del data from redis,key is {},identify is {}",key, ids.toString());
		}
	}

	@Override
	public T findEntityById(Serializable id) {
		String key = super.getCla().getName();
		T t = null;
		boolean flag = false;
		try{
			t = (T) this.redisService.hgetObj(key, id.toString(), super.getCla());
			if(null != t){
				logger.info("success to find {} from redis, id is {}" ,key, id);
				return t ;
			}else{
				flag = true;
			}
		}catch(Exception e){
			flag = true;
		}
		if(flag){
			logger.info("failed to find {} from redis, id is {}" ,key, id);
			t = super.findEntityById(id);
			logger.info("success to find {} from db, id is {}" ,key, id);
		}
		return t;
	}

	@Override
	public T findEntityByString(String string, Object value) {
		return super.findEntityByString(string, value);
	}

	@Override
	public List<T> findAll() {
		String key = super.getCla().getName();
		List<T> list = new ArrayList<T>();
		try{
			Map<String , T> all = this.redisService.hgetAll(key,super.getCla());
			if(null == all || all.size() == 0)
				return null;
			Set<Entry<String, T>> entrySet = all.entrySet();
			for (Entry<String, T> entry : entrySet) {
				list.add(entry.getValue());
			}
			logger.info("success to find all {} data from redis, total count is {}",key, list.size());
			return list ;
		}catch(Exception e){
			logger.info("failed to find all {} data from redis," ,key);
			list = super.findAll();
			logger.info("success to find {} from db, id is {}" , key, list.size());
			return list;
		}
	}

	@Override
	public Page<T> findPage(BaseQuery baseQuery) {
		// TODO Auto-generated method stub
		return super.findPage(baseQuery);
	}

	@Override
	public List<T> findEntityByIds(Serializable[] ids) {
		// TODO Auto-generated method stub
		return super.findEntityByIds(ids);
	}

	@Override
	public Page<T> findPageByHqlMap(BaseQuery baseQuery) {
		// TODO Auto-generated method stub
		return super.findPageByHqlMap(baseQuery);
	}
	
}
