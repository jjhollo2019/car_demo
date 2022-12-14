// Jeremy Holloway
package com.car_demo.car_demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import lombok.AllArgsConstructor;

@Tag(name = "Car Controller", description = "CRUD operations for car inventory")
@AllArgsConstructor
@RestController
// set root path to /car
@RequestMapping("/car")
public class CarController {
    
    private CarService carService;

    /**
     * @return all cars stored in the db with http code 200 (ok)
     */
    @ApiResponse(responseCode = "200", description = "Successful retrieval of cars", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class))))
    @Operation(summary = "Retrieves cars", description = "Provides a list of all cars in inventory")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Car>> getInventory() {
        // retrieve inventory from service class
        List<Car> inventory = carService.getInventory();
        // send inventory and 200 status code back to user
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    /**
     * @param id Long number unique to an entry for a Car
     * @return the car object with corresponding id with http code 200 (ok)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "404", description = "Car doesn't exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        // get car from service class
        Car car = carService.getCarById(id);
        // send car entity back to user
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    /**
     * @param car updated car object 
     * @param result binding result for cross field validation
     * @return car object from the db with matching id from updated car object and http code 202 (accepted)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful update of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request: unsuccessful submission", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @PutMapping
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car, BindingResult result) {  
        // call update function and return the result with http code 202 (accepted)    
        return new ResponseEntity<>(carService.updateCar(car), HttpStatus.ACCEPTED);
    }

    /**
     * @param car object to be inserted into db
     * @return http code 201 (created)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful creation of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request: unsuccessful submission", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @PostMapping
    public ResponseEntity<HttpStatus> createCar(@Valid @RequestBody Car car) {
        // save the car using the service class
        carService.saveCar(car);
        // return http code 201 (created)
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    /**
     * @param carId Long number unique to an entry for a Car
     * @param salesId Long number unique to an entry for a SalesEmployee
     * @return http code 202 (accepted)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful addition of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "400", description = "Bad Request: unsuccessful addition of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @PostMapping("/{carId}/sales/{salesId}")
    public ResponseEntity<HttpStatus> addSales(@PathVariable Long carId, @PathVariable Long salesId) {
        // call service layer function
        carService.addSalesEmployee(carId, salesId);
        // return http code 202 (accepted)
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
    
    /**
     * @param id Long number unique to an entry for a Car
     * @return http code 204 (no content)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful deletion of car", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))),
        @ApiResponse(responseCode = "404", description = "No car found with that id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        // call delete function from the service layer
        carService.deleteCar(id);
        // return http code 204 (no content)
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
