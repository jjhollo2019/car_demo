/* Jeremy Holloway
 * These tests check the controller handling all of my service requests
 * I can use these to verify the basic functions of the back end work after changes
 */

package com.car_demo.car_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.car_demo.car_demo.controller.CarController;
import com.car_demo.car_demo.controller.MechanicController;
import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.definitions.Mechanic;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarDemoApplicationTests {

	// create a private instance of the controller
	@Autowired
	private CarController carController;

	@Autowired
	private MechanicController mechanicController;

	// the object mapper is used to turning objects into JSON
	@Autowired
	ObjectMapper objectMapper;

	// create a private instance of the Mock Mvc 
	@Autowired
	private MockMvc mockMvc;

	
	/**
	 * test to ensure controller and mock are not null
	 */
	@Test
	void contextLoads() {
		// assert controllers and mock are not null
		assertNotNull(carController);
		assertNotNull(mechanicController);
		assertNotNull(mockMvc);
	}

	/*
	 *************************************************************************************************
	 * CRUD tests for car controller
	 ************************************************************************************************* 
	 */

	/**
	 * test of adding a car at the controller
	 * @throws Exception if request is improperly formatted
	 */
	@Test
	public void testSuccessfulCarSubmission() throws Exception {
		// create a new car object
		Car car = new Car("dodge", "ram", "2020", 27000);
		// build a post request for the new car object
		RequestBuilder request = MockMvcRequestBuilders.post("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(car));

		// perform request and expect an HTTP code 201 (created)
		mockMvc.perform(request)
			.andExpect(status().isCreated());
	}

	/**
	 * test of adding a car with a bad condition at the controller
	 * @throws Exception if request is improperly formatted
	 */
	@Test
	public void testUnsuccessfulCarSubmission() throws Exception {
		// create a new car object
		Car car = new Car("dodge", "  ", "2020", 27000);
		// build a post request for the new car object
		RequestBuilder request = MockMvcRequestBuilders.post("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(car));

		// perform request and expect HTTP code 400 (bad request)
		mockMvc.perform(request)
			.andExpect(status().isBadRequest());
	}

	/**
	 * test of getting all cars in the inventory at the controller
	 * @throws Exception
	 */
	@Test
	public void testGetCars() throws Exception {
		// build get request to get inventory
		RequestBuilder request = MockMvcRequestBuilders.get("/car/all");

		// perform request and expect HTTP code 200 (ok)
		mockMvc.perform(request)
			.andExpect(status().isOk());
	}

	/**
	 * test of updating a car at the controller
	 * @throws Exception
	 */
	@Test
	public void testUpdateCar() throws Exception {
		// create a new car object
		Car car = new Car("dodge", "ram", "2020", 27000);
		// build a post request for the new car object
		RequestBuilder request = MockMvcRequestBuilders.post("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(car));

		// perform request and expect an HTTP code 201 (created)
		mockMvc.perform(request)
			.andExpect(status().isCreated());

		car.setProduction_year("2022");
		RequestBuilder request_update = MockMvcRequestBuilders.put("/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(car));

		// perform request, expect HTTP code 202 (accepted) and the values to be changed in the response object
		mockMvc.perform(request_update)
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.production_year").value("2022"));
	}


	/*
	 *************************************************************************************************
	 * CRUD tests for mechanic controller
	 ************************************************************************************************* 
	 */

	/**
	 * test of add function at the controller level
	 * @throws Exception if mechanic object is not well formed
	 */
	@Test
	public void testSuccessfulMechanicSubmission() throws Exception {
		// create a mock mechanic object
		Mechanic mMechanic = new Mechanic("Donald Blythe", 56000);
		// create a mock request to the mechanic controller
		RequestBuilder request = MockMvcRequestBuilders.post("/mechanic")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(mMechanic));

		// perform request and expect an HTTP code 201 (created)
		mockMvc.perform(request)
			.andExpect(status().isCreated());
	}

	/**
	 * test of add function at the controller level
	 * @throws Exception if mechanic object is not well formed
	 */
	@Test
	public void testUnsuccessfulMechanicSubmission() throws Exception {
		// create a bad mock mechanic object
		Mechanic mMechanic = new Mechanic("   ", 56000);
		// create a mock request to the mechanic controller
		RequestBuilder request = MockMvcRequestBuilders.post("/mechanic")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(mMechanic));

		// perform request and expect an HTTP code 400 (bad request)
		mockMvc.perform(request)
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetMechanics() throws Exception {
		// build get request to get inventory
		RequestBuilder request = MockMvcRequestBuilders.get("/mechanic/all");

		// perform request and expect HTTP code 200 (ok)
		mockMvc.perform(request)
			.andExpect(status().isOk());
	}

	/**
	 * test of update function at the controller level
	 * @throws Exception
	 */
	@Test
	public void testUpdateMechanic() throws Exception {
		// create a mock mechanic to be saved in the db
		Mechanic mMechanic = new Mechanic("Donald Blythe", 45000);
		// build a post request for the new mechanic object
		RequestBuilder request = MockMvcRequestBuilders.post("/mechanic")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(mMechanic));

		// perform request and expect an HTTP code 201 (created)
		mockMvc.perform(request)
			.andExpect(status().isCreated());

		mMechanic.setSalary(65000);
		RequestBuilder request_update = MockMvcRequestBuilders.put("/mechanic")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(mMechanic));

		// perform request, expect HTTP code 202 (accepted) and the values to be changed in the response object
		mockMvc.perform(request_update)
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.salary").value(65000));
	}
}
