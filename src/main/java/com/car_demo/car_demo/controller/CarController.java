package com.car_demo.car_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.service.CarService;

@RestController
public class CarController {
    
    @Autowired
    private CarService carService;

    @GetMapping("/car/all")
    public ResponseEntity<List<Car>> getInventory() {
        // retrieve inventory from service class
        List<Car> inventory = carService.getInventory();
        // send inventory and 200 status code back to user
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable String id) {
        // get car from service class
        Car car = carService.getCarById(id);
        // send car entity back to user
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        // save the car using the service class
        carService.saveCar(car);
        // return object and created status code to user
        return new ResponseEntity<Car>(car, HttpStatus.CREATED);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
