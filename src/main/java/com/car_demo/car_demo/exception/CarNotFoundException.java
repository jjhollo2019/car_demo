package com.car_demo.car_demo.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String id) {
        super("The id '" + id + "' does not exist in our records");
    }
}
