package com.flash.area.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flash.area.dao.AreaDao;
import com.flash.area.domain.Area;
import com.flash.area.service.AreaService;
import com.flash.area.tool.comparator.DefaultAreaComparator;
import com.flash.area.tool.query.AreaQuery;
import com.flash.base.dao.CommonDao;
import com.flash.base.service.impl.CommonServiceImpl;
import com.flash.base.tool.page.Page;
import com.flash.service.redis.RedisService;

@Service("areaService")
public class AreaServiceImpl extends CommonServiceImpl<Area> implements
		AreaService {

	/**
	 * 存入redis缓存中的数据。
	 */
	private static String ALL_AREAS = "ALL_AREAS";

	private static String ALL_AREAS_KEY_BY_PROVINCE = "ALL_AREAS_KEY_BY_PROVINCE";

	private static ObjectMapper MAPPER = new ObjectMapper();
	@Resource(name = "areaDao")
	private AreaDao areaDao;

	@Override
	public CommonDao<Area> getCommonDao() {
		return this.areaDao;
	}

	@Resource(name = "redisService")
	private RedisService redisService;

	/**
	 * 查询所有的 地区，并将其放入redis缓存 返回的Map是以字母为顺序
	 * 
	 * @Override
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<Area>> queryAllAreasReturnMap() {
		Map<String, List<Area>> map = new HashMap<String, List<Area>>();
		List<Area> areas = null;
		// 读缓存
		String data = redisService.get(ALL_AREAS);
		if (StringUtils.isNotBlank(data)) {
			try {
				Area[] readValue = MAPPER.readValue(data, Area[].class);
				areas = Arrays.asList(readValue);
				map = pushAreaListInMap(areas);
				return map;
			} catch (Exception e) {
				System.out.println("redis服务器关闭");
			}
		}
		// 没有缓存从数据库中查找
		areas = findAll();
		Collections.sort(areas, new DefaultAreaComparator());
		map = pushAreaListInMap(areas);

		// 写入缓存
		try {
			this.redisService.set(ALL_AREAS, MAPPER.writeValueAsString(areas),
					60 * 60 * 24 * 30);
		} catch (JsonProcessingException e) {
			System.out.println("redis服务器关闭");
		}
		return map;
	}

	/**
	 * 查询所有地区 走缓存 返回一个地区List集合
	 * 
	 * @return 所有地区的List集合
	 */
	@SuppressWarnings("unchecked")
	public List<Area> queryAllAreasReturnList() {
		List<Area> areas = null;
		// 读缓存
		String data = redisService.get(ALL_AREAS);
		if (StringUtils.isNotBlank(data)) {
			try {
				Area[] readValue = MAPPER.readValue(data, Area[].class);
				areas = Arrays.asList(readValue);
				return areas;
			} catch (Exception e) {
				System.out.println("redis服务器关闭");
			}
		}
		// 没有缓存从数据库中查找
		areas = findAll();
		Collections.sort(areas, new DefaultAreaComparator());
		// 写入缓存
		try {
			this.redisService.set(ALL_AREAS, MAPPER.writeValueAsString(areas),
					60 * 60 * 24 * 30);
		} catch (JsonProcessingException e) {
			System.out.println("redis服务器关闭");
		}
		return areas;
	}

	/**
	 * 将list中的area放进一个map中 ，key为首字母 value为一个小Map,这个map的key为排序，value为
	 * 
	 * @return
	 */
	private Map<String, List<Area>> pushAreaListInMap(List<Area> areas) {
		Map<String, List<Area>> map = new HashMap<String, List<Area>>();
		for (Area area : areas) {
			String firstCode = area.getPinyin().charAt(0) + "";
			if (map.containsKey(firstCode)) {
				List<Area> list = map.get(firstCode);
				// 包含此key的地区，需将此地区放入对应的集合
				list.add(area);
				map.put(firstCode, list);
			} else {
				// 不包含此key的地区，需新建立这个地区对应的集合
				List<Area> list = new ArrayList<Area>();
				list.add(area);
				map.put(firstCode, list);
			}
		}
		return map;
	}

	/**
	 * 将省份作为key
	 * 
	 * @tip 此方法未启用
	 * @param areas
	 * @return
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private Map<String, List<Area>> pushAreaListInMapKeyByProvince(
			List<Area> areas) {
		Map<String, List<Area>> map = new HashMap<String, List<Area>>();

		for (Area area : areas) {
			// 将省份作为key
			boolean isProvince = area.getIsRoot();
			String areaName = area.getName();
			if (isProvince) {
				// 是省份，则直接将该省份作为key放入map
				if (!map.containsKey(areaName)) {
					map.put(areaName, new ArrayList<Area>());
				}
			} else {
				// 是城市，则找相对于的省份
			}

			String firstCode = area.getPinyin().charAt(0) + "";
			if (map.containsKey(firstCode)) {
				List<Area> list = map.get(firstCode);
				// 包含此key的地区，需将此地区放入对应的集合
				list.add(area);
				map.put(firstCode, list);
			} else {
				// 不包含此key的地区，需新建立这个地区对应的集合
				List<Area> list = new ArrayList<Area>();
				list.add(area);
				map.put(firstCode, list);
			}
		}
		return map;
	}

	/**
	 * 从redis中查询出所有数据，以key为省份 value为地区的map 按照sort 排序来存放
	 * 
	 * @Override TODO
	 */
	public Page<Area> queryAreasFromRedis(AreaQuery areaQuery) {
		List<Area> areas = null;
		// 读缓存
		String data = redisService.get(ALL_AREAS);
		if (StringUtils.isNotBlank(data)) {
			try {
				Area[] readValue = MAPPER.readValue(data, Area[].class);
				areas = Arrays.asList(readValue);
				
				Page<Area> page = new Page<Area>(areaQuery.getCurrentPage(), areaQuery.getPageSize(), areas.size());
				
				page.setPageData(areas.subList(Math.min(page.getPageStart(), areas.size()), Math.min(page.getPageEnd(), areas.size())));
			} catch (Exception e) {
				System.out.println("redis服务器关闭");
			}
		}

		// 从数据库中查询
		areas = this.findAll();
		Collections.sort(areas, new DefaultAreaComparator());
		Page<Area> page = new Page<Area>(areaQuery.getCurrentPage(), areaQuery.getPageSize(), areas.size());
		
		page.setPageData(areas.subList(Math.min(page.getPageStart(), areas.size()), Math.min(page.getPageEnd(), areas.size())));
		
		// 写入缓存
		try {
			this.redisService.set(ALL_AREAS, MAPPER.writeValueAsString(areas),
					60 * 60 * 24 * 30);
		} catch (JsonProcessingException e) {
			System.out.println("redis服务器关闭");
		}
		return page;
	}
}
