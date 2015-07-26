package com.flash.system.service;

import com.flash.base.web.dto.system.VersionUpdateDto;
import com.flash.base.web.form.system.CheckVersionForm;
import com.flash.base.web.form.system.FeedBackForm;

/**
 * 系统级别的service
 * @author lonaking
 */
public interface SystemService {

	/**
	 * 查询当前手机app端版本是否更新
	 * @author lonaking
	 * @param versionForm 提交的一个版本参数表
	 * @return
	 */
	public VersionUpdateDto checkVersion(CheckVersionForm versionForm);

	/**
	 * 保存意见反馈信息
	 * @author lonaking
	 * @param feedBackForm 保存意见反馈信息
	 */
	public void saveFeedBackInfo(FeedBackForm feedBackForm);
}
