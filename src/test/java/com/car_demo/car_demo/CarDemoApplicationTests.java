package com.car_demo.car_demo;

import java.security.Provider.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.car_demo.car_demo.controller.CarController;
import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarDemoApplicationTests {

	@Autowired
	private CarController controller;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(mockMvc);
	}

	@Test
	public void testSuccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(new Car("1", "dodge", "ram", "2020", 27000)));

		mockMvc.perform(request)
			.andExpect(status().isCreated());
	}

	@Test
	public void testUnsuccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(new Car("1", " ", "ram", "2020", 27000)));

		mockMvc.perform(request)
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetCars() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/car/all");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful());
	}

}
