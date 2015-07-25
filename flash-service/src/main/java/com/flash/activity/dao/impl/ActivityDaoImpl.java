package com.flash.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.activity.dao.ActivityDao;
import com.flash.activity.domain.Activity;
import com.flash.base.dao.impl.CommonDaoImpl;

@Repository("activityDao")
public class ActivityDaoImpl extends CommonDaoImpl<Activity> implements ActivityDao{

}
