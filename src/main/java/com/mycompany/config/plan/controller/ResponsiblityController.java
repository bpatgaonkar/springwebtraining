/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.config.plan.controller;

import com.mycompany.config.plan.Plan;
import com.mycompany.config.plan.Responsibility;
import com.mycompany.config.plan.ResponsibilityList;
import com.mycompany.config.plan.service.PlanService;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 160665
 */
@Controller
@RequestMapping("/plan")
public class ResponsiblityController {

    @Autowired
    PlanService planService;

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/{id}/responsiblity/list", method = RequestMethod.GET)
    public @ResponseBody
    ResponsibilityList getResponsiblityByPlanName(@PathVariable String id, ModelMap model) {
        Plan plan = planService.getPlanById(id);
        Set<Responsibility> responsiblities = plan.getResponsibilities();
        ResponsibilityList respList = new ResponsibilityList();
        respList.setResponsiblities(responsiblities);
        respList.setNoOfRecords(responsiblities.size());
        return respList;
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/responsiblity/{resid}", method = RequestMethod.GET)
    public @ResponseBody
    Responsibility getResponsiblityById(@PathVariable String resid, ModelMap model) {

        List<Responsibility> list=planService.getResponsiblityById(resid);
        return list.get(0);
    }

    public PlanService getPlanService() {
        return planService;
    }

    public void setPlanService(PlanService planService) {
        this.planService = planService;
    }

}
