package com.flash.sso.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.sso.dao.SsoSessionDao;
import com.flash.sso.domain.SsoSession;

@Repository("ssoSessionDao")
public class SsoSessionDaoImpl extends CommonDaoImpl<SsoSession> implements SsoSessionDao{

}
