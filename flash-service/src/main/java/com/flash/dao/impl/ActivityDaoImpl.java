package com.flash.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.dao.ActivityDao;
import com.flash.domain.Activity;

@Repository("activityDao")
public class ActivityDaoImpl extends CommonDaoImpl<Activity> implements ActivityDao{

}
