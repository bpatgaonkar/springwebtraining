package com.mycompany.config.plan.test;

import com.mycompany.config.plan.controller.ResponsiblityController;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:/com/mycompany/config/applicationContext.xml"})

public class ResponsiblityControllerTest {
	@Autowired
	ResponsiblityController responsiblityController; 
	private static final Logger log = Logger.getLogger(ResponsiblityControllerTest.class);
	private MockMvc mockMvc;

	public ResponsiblityController getResponsiblityController() {
		return responsiblityController;
	}


	public void setResponsiblityController(ResponsiblityController responsiblityController) {
		this.responsiblityController = responsiblityController;
	}

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(getResponsiblityController()).build();
	}


	@Test
	public void testGgetResponsiblityById() throws Exception {

		mockMvc.perform(get("/plan/responsiblity/18"))
				.andExpect(status().isOk());
				//.andExpect(content().contentType(MediaType.APPLICATION_JSON));
				//.andExpect(jsonPath("$.name").value("PLMainLoanPlan1"));	

	}
}
