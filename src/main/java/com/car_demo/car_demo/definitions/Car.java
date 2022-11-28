package com.car_demo.car_demo.definitions;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

/* This class will serve as the basic data type for all cars
 * I'm defining all of the data points about a car in this class
 */
public class Car {
    
    //randomly generated id value
    private String id;
    
    //car make
    @NotBlank(message = "Car make cannot be blank")
    private String make;
    
    //car model
    @NotBlank(message = "Car model cannot be blank")
    private String model;

    @NotBlank(message = "Car year cannot be blank")
    private String year;

    //car mileage
    private int mileage;

    // Constructors
    public Car(String id, String make, String model, String year, int mileage) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    public Car(String make, String model, String year, int mileage) {
        this.id = UUID.randomUUID().toString();
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }    

}
