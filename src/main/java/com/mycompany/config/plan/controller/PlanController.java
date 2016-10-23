package com.mycompany.config.plan.controller;

import com.mycompany.config.plan.Plan;
import com.mycompany.config.plan.PlanList;
import com.mycompany.config.plan.service.PlanService;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	PlanService planService; 
	
	public PlanService getPlanService() {
		return planService;
	}

	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}

	@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody Plan getPlanByName(@PathVariable String name, ModelMap model) {
		Plan plan= planService.getPlanByName(name);
                Hibernate.initialize(plan);
		return plan;
	}
	
	
	@RequestMapping(value="/list", produces = {MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
	public @ResponseBody PlanList getAllPlan(ModelMap model) {
		List<Plan> plist= planService.getAllPlans();
		PlanList plans = new PlanList();
		plans.setPlans(plist);
		plans.setNoOfRecords(plist.size());
		return plans;
	}
	@RequestMapping(value="/create",  method = RequestMethod.POST)
	public @ResponseBody Long createPlan(@RequestBody Plan plan, ModelMap model){
		return planService.addPlan(plan);
	}
	
	@RequestMapping( value = "/{id}",  method = RequestMethod.PUT)
	public @ResponseBody void saveOrUpdatePlan(@RequestBody Plan plan,@PathVariable String id, ModelMap model){
		if(plan!=null){
			if(id!=null){
				plan.setPlanid(new BigDecimal(id));
			}
			planService.saveOrUpdatePlan(plan);
		}
	}	
}
