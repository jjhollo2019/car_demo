package com.car_demo.car_demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CarMakeValidator.class)
public @interface CarMake {
    String message() default "Invalid Make";
        Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
