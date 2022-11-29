package com.car_demo.car_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Mechanic Controller", description = "CRUD operations for mechanics")
@AllArgsConstructor
@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    
    
}
