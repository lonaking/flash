package com.flash.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.form.activity.ActivityAddForm;
import com.flash.base.web.form.activity.ActivityUpdateForm;
import com.flash.base.web.tool.query.ActivityQuery;
import com.flash.dao.ActivityDao;
import com.flash.domain.Activity;
import com.flash.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Resource(name = "activityDao")
	private ActivityDao activityDao;

	/**
	 * 条件查询活动列表
	 */
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

	/**
	 * 根据活动id查询活动详情
	 */
	@Override
	public ActivityDto queryActivityDetailById(Serializable activityId) {
		Activity activity = this.activityDao.findEntityById(activityId);
		if(null != activity){
			ActivityDto activityDto = new ActivityDto();
			BeanUtils.copyProperties(activity, activityDto);
			return activityDto;
		}else{
			return null;
		}
	}

	/**
	 * 添加一条活动信息
	 */
	@Override
	public ActivityDto addActivity(ActivityAddForm activityForm) {
		Activity activity = new Activity();
		BeanUtils.copyProperties(activityForm, activity);
		activity.setUpdateTime(System.currentTimeMillis());
		activity.setCreateTime(System.currentTimeMillis());
		activity.setStatus(0);;
		this.activityDao.saveEntity(activity);
		//转换dto
		ActivityDto activityDto = new ActivityDto();
		BeanUtils.copyProperties(activity, activityDto);
		return activityDto;
	}

	/**
	 * 更新一条活动记录
	 */
	@Override
	public ActivityDto updateActivity(ActivityUpdateForm activityUpdateForm) {
		Activity activity = new Activity();
		BeanUtils.copyProperties(activityUpdateForm, activity);
		activity.setUpdateTime(System.currentTimeMillis());
		this.activityDao.updateEntity(activity);
		//转换dto
		ActivityDto activityDto = new ActivityDto();
		BeanUtils.copyProperties(activity, activityDto);
		return activityDto;
	}
	

}
