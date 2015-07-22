package com.flash.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.config.dao.ConfigDao;
import com.flash.config.domain.Config;

/**
 * @author Leon
 * @date 2014年12月28日
 * @since v 1.0
 */
@Repository("configDao")
public class ConfigDaoImpl extends CommonDaoImpl<Config> implements ConfigDao{
	
}
