package com.flash.area.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.area.dao.AreaDao;
import com.flash.area.domain.Area;
import com.flash.base.dao.impl.CommonDaoImpl;
@Repository("areaDao")
public class AreaDaoImpl extends CommonDaoImpl<Area> implements AreaDao{

}
