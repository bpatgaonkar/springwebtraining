package com.mycompany.config.plan.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mycompany.config.plan.controller.ActivityController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:com/mycompany/config/applicationContext.xml"})
public class ActivityControllerTest {
	@Autowired
	ActivityController activityController;
	private static final Logger log = Logger.getLogger(ActivityControllerTest.class);
	private MockMvc mockMvc;
	
	
	public ActivityController getActivityController() {
		return activityController;
	}
	public void setActivityController(ActivityController activityController) {
		this.activityController = activityController;
	}
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(getActivityController()).build();
	}
	//@Test
	public void testActivityByTypeCode() throws Exception  {
		
		String response = mockMvc.perform(get("/plan/responsiblity/activity/FHA_MIP"))
		.andExpect(status().isOk())
		.andExpect(
				content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
		log.debug(response);		
	}

	@Test
	public void testGetActivities() throws Exception {	
		String response = mockMvc.perform(get("/plan/8/responsiblity/18/activity/list"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andReturn().getResponse().getContentAsString();//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				log.debug("response - "+response);
	}
	
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }	
}
