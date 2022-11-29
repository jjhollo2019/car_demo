package com.car_demo.car_demo.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("The id '" + String.valueOf(id) + "' does not exist in our records");
    }
}
