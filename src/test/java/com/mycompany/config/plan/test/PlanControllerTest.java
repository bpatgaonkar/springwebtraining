package com.mycompany.config.plan.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mycompany.config.plan.Plan;
import com.mycompany.config.plan.controller.PlanController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:/com/mycompany/config/applicationContext.xml"})

public class PlanControllerTest {
	@Autowired
	PlanController planController; 
	private static final Logger log = Logger.getLogger(PlanControllerTest.class);
	private MockMvc mockMvc;

	public PlanController getPlanController() {
		return planController;
	}


	public void setPlanController(PlanController planController) {
		this.planController = planController;
	}

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(getPlanController()).build();
	}


	@Test
	public void testGetPlanName() throws Exception {

		mockMvc.perform(get("/plan/PLMainLoanPlan1"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("PLMainLoanPlan1"));	
                //String response=mockMvc.perform(get("/plan/PLMainLoanPlan1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
                //System.out.println(response);
	}
	
	@Test
	public void testGetAllPlan() throws Exception {	
		String response = mockMvc.perform(get("/plan/list"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_XML))
		.andReturn().getResponse().getContentAsString();//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		log.debug("testGetAllPlan - "+response);
	}
	
	//@Test
	public void testAddPlan() throws Exception {
		Plan plan = new Plan();
		plan.setPlanid(new BigDecimal(11));
		plan.setName("test");
		plan.setVersion(1);
		plan.setSessionid(new BigDecimal(-1));
		plan.setLstmoddttm(new Date());
		String response = mockMvc.perform(post("/plan/create").contentType(MediaType.APPLICATION_JSON)
					.content(ActivityControllerTest.convertObjectToJsonBytes(plan)))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();
		log.debug("testAddPlan -"+response);
	}
	
	//@Test
	public void testSaveOrUpdatePlan() throws Exception {
		Plan plan = new Plan();
		//plan.setPlanid(new BigDecimal(11));
		plan.setName("test2");
		plan.setVersion(5);
		plan.setSessionid(new BigDecimal(-1));
		plan.setLstmoddttm(new Date());
		String response = mockMvc.perform(put("/plan/11").contentType(MediaType.APPLICATION_JSON)
					.content(ActivityControllerTest.convertObjectToJsonBytes(plan)))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();
		log.debug("testSaveOrUpdatePlan -"+response);
	}
}
