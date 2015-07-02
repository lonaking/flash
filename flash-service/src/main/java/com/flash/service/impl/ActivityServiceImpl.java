package com.flash.service.impl;

import javax.annotation.Resource;

import com.flash.base.dao.CommonDao;
import com.flash.base.service.impl.CommonServiceImpl;
import com.flash.dao.ActivityDao;
import com.flash.domain.Activity;
import com.flash.service.ActivityService;

public class ActivityServiceImpl extends CommonServiceImpl<Activity> implements ActivityService{

	@Resource(name = "activityDao")
	private ActivityDao activityDao;
	@Override
	public CommonDao<Activity> getCommonDao() {
		return this.activityDao;
	}

}
