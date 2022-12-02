// Jeremy Holloway
package com.car_demo.car_demo.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// implement a constraint for CarMake and a string value
public class CarMakeValidator implements ConstraintValidator<CarMake, String> {

    // list of car manufacturers
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

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // if the value passed matches a valid make return true
        for(String make : makes){
            if(make.equalsIgnoreCase(value)) return true;
        }
        // default to return false
        return false;
    }
}
