package com.project2.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project2.domain.Fixture;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:fixture-schema.sql", "classpath:fixture-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FixtureControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Fixture testMatch = new Fixture(null, "Breakout", "20XX", 1700L);
		String testMatchAsJSON=this.mapper.writeValueAsString(testMatch);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testMatchAsJSON);
		
		Fixture testCreatedMatch = new Fixture(1L, "Breakout", "20XX", 1700L);
		String testCreatedMatchAsJSON = this.mapper.writeValueAsString(testCreatedMatch); 
		ResultMatcher checkStatus = status().isCreated(); //status 201 - created
		ResultMatcher checkBody = content().json(testCreatedMatchAsJSON); //checks if the body matches my testCreatedMatchAsJson
		
		// sends the request and then checks the status and the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Fixture> testMatchs = List.of(new Fixture(1L, "Mannfield", "Snowy", 4L), new Fixture(2L, "Forbidden Temple", "Sunset", 1L));
        String json = this.mapper.writeValueAsString(testMatchs);
        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(json);
        
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetById() throws Exception {
		RequestBuilder req = get("/getById/1");
		Fixture testMatchs = new Fixture(1L, "Mannfield", "Snowy", 4L);
        String json = this.mapper.writeValueAsString(testMatchs);
        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(json);
        
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	
	@Test
	void testUpdate() throws Exception {
		Fixture testMatch = new Fixture(null, "Octane", "Spectre", 1000L);
		String testMatchAsJSON=this.mapper.writeValueAsString(testMatch);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testMatchAsJSON);
		Fixture testMatchs = new Fixture(1L, "Octane", "Spectre", 1000L);
		String json = this.mapper.writeValueAsString(testMatchs);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder req = delete("/remove/1");

		ResultMatcher checkStatus = status().isNoContent(); //status 201 - created
		
		// sends the request and then checks the status
		this.mvc.perform(req).andExpect(checkStatus);
		
	}
}
