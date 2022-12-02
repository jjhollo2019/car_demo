// Jeremy Holloway
package com.car_demo.car_demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// target a field 
@Target(ElementType.FIELD)
// only retain during runtime
@Retention(RetentionPolicy.RUNTIME)
// delcare my validator as the constraint 
@Constraint(validatedBy = CarMakeValidator.class)
public @interface CarMake {
    // set default parameters
    String message() default "Invalid Make";
        Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
