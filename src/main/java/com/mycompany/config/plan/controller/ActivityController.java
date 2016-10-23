package com.mycompany.config.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.config.plan.Activity;
import com.mycompany.config.plan.ActivityList;
import com.mycompany.config.plan.service.ActivityService;

@Controller
@RequestMapping("/plan")
public class ActivityController {
	@Autowired
	ActivityService activityService;

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/responsiblity/activity/{typeCode}", method = RequestMethod.GET)
	public @ResponseBody Activity getActivityForTypeCode(@PathVariable String typeCode, ModelMap model){
		return activityService.getActivityForTypeCode(typeCode);
	}
	@RequestMapping(value="/{planid}/responsiblity/{respid}/activity/list", produces = {MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
	public @ResponseBody ActivityList getActivitiesByPlanResponsibility(@PathVariable String planid, @PathVariable String respid, ModelMap model) {
		List<Activity> alist= activityService.getActivitiesByPlanResponsibility(planid, respid);
		ActivityList activityList = new ActivityList();
		activityList.setActivities(alist);
		activityList.setNoOfRecords(alist.size());
		return activityList;
	}
}
