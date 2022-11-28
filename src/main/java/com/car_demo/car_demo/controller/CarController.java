package com.car_demo.car_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.exception.ErrorResponse;
import com.car_demo.car_demo.service.CarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Car Controller", description = "CRUD operations for car inventory")
@RestController
public class CarController {
    
    @Autowired
    private CarService carService;

    @ApiResponse(responseCode = "200", description = "Successful retrieval of cars", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class))))
    @Operation(summary = "Retrieves cars", description = "Provides a list of all cars in inventory")
    @GetMapping(value = "/car/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Car>> getInventory() {
        // retrieve inventory from service class
        List<Car> inventory = carService.getInventory();
        // send inventory and 200 status code back to user
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "404", description = "Car doesn't exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @GetMapping(value = "/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable String id) {
        // get car from service class
        Car car = carService.getCarById(id);
        // send car entity back to user
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful creation of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request: unsuccessful submission", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        // save the car using the service class
        carService.saveCar(car);
        // return object and created status code to user
        return new ResponseEntity<Car>(car, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful deletion of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request: unsuccessful submission", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @DeleteMapping("/car/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
