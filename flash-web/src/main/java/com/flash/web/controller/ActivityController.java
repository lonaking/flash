package com.flash.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flash.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Resource(name = "activityService")
	private ActivityService activityService;
}
