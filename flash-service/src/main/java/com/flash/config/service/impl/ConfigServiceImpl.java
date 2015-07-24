package com.flash.config.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flash.base.dao.CommonDao;
import com.flash.base.service.impl.CommonServiceImpl;
import com.flash.config.dao.ConfigDao;
import com.flash.config.domain.Config;
import com.flash.config.service.ConfigService;
import com.flash.config.tool.comparator.DefaultConfigComparator;
@Service("configService")
public class ConfigServiceImpl extends CommonServiceImpl<Config> implements ConfigService{

	@Resource(name="configDao")
	private ConfigDao configDao;
	@Override
	public CommonDao<Config> getCommonDao() {
		return this.configDao;
	}
	private static ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	@Transactional
	public void updateConfigParameterMap(Map<String, Config> parameterMap) {
		Set<Entry<String, Config>> entrySet = parameterMap.entrySet();
		for (Entry<String, Config> entry : entrySet) {
			super.updateEntity(entry.getValue());
		}
	}
	@Override
	public List<Config> showConfigsBySort(Map<String, Config> configs,
			String app, DefaultConfigComparator defaultConfigComparator) {
		
		List<Config> list = new ArrayList<Config>();
		for (Entry<String, Config> entry : configs.entrySet()) {
			if(entry.getValue().getModule().equals(app)){
				if(StringUtils.isNotBlank(entry.getValue().getListJson())){
					try {
						Map mapData = this.MAPPER.readValue(entry.getValue().getListJson(), Map.class);
						Config config = entry.getValue();
						System.out.println(config);
						config.setMapData(mapData);
						entry.setValue(config);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(entry.getValue());
			}
		}
		Collections.sort(list, defaultConfigComparator);
		return list;
	}
	@Override
	public Config selectOneConfigBySign(String sign) {
		Config config = this.configDao.findEntityByString("sign", sign);
		return config;
	}
}
