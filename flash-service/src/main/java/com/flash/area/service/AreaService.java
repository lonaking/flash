package com.flash.area.service;

import java.util.List;
import java.util.Map;

import com.flash.area.domain.Area;
import com.flash.base.service.CommonService;

public interface AreaService extends CommonService<Area>{
	
	/**
	 * 查询出所有的地区
	 * @return
	 */
	public Map<String, List<Area>> queryAllAreasReturnMap();

	/**
	 * 查询所有地区 并刷新redis中的数据
	 * @return
	 */
	public List<Area> queryAllAreasReturnList();

}
