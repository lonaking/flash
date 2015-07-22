package com.flash.config.service;

import java.util.List;
import java.util.Map;

import com.flash.base.service.CommonService;
import com.flash.config.domain.Config;
import com.flash.config.tool.comparator.DefaultConfigComparator;

public interface ConfigService extends CommonService<Config>{

	public void updateConfigParameterMap(Map<String, Config> parameterMap);

	public List<Config> showConfigsBySort(Map<String, Config> configs,
			String app, DefaultConfigComparator defaultConfigComparator);

	/**
	 * 根据sign获取配置信息
	 * @author lonaking
	 * @param sign
	 * @return
	 */
	public Config selectOneConfigBySign(String sign);
	
}
