package com.car_demo.car_demo.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarMakeValidator implements ConstraintValidator<CarMake, String> {
    List<String> makes = Arrays.asList(
        "acura",
        "audi",
        "buick",
        "bmw",
        "cadillac",
        "chevrolet",
        "gmc",
        "honda",
        "chrysler",
        "dodge",
        "jeep",
        "lexus",
        "ram",
        "ford",
        "lincoln"
    );


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for(String make : makes){
            if(make.equalsIgnoreCase(value)) return true;
        }
        return false;
    }
    
}
