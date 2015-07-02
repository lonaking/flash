package com.flash.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.tool.query.ActivityQuery;
import com.flash.dao.ActivityDao;
import com.flash.domain.Activity;
import com.flash.service.ActivityService;

public class ActivityServiceImpl implements ActivityService{

	@Resource(name = "activityDao")
	private ActivityDao activityDao;

	@Override
	public Page<ActivityDto> queryActivitiesByQuery(ActivityQuery query) {
		query.buildOrderBy("endTime", BaseQuery.DESC);
		Page<Activity> page = this.activityDao.findPage(query);
		Page<ActivityDto> pageResult = new Page<ActivityDto>();
		BeanUtils.copyProperties(page, pageResult, new String[]{"pageData"});
		List<Activity> pageData = page.getPageData();
		List<ActivityDto> pageResultData = new ArrayList<ActivityDto>();
		for (Activity activity : pageData) {
			ActivityDto activityDto = new ActivityDto();
			BeanUtils.copyProperties(activity, activityDto);
			pageResultData.add(activityDto);
		}
		pageResult.setPageData(pageResultData);
		return pageResult;
	}
	

}
