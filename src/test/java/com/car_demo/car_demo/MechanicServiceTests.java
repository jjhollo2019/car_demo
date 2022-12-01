package com.car_demo.car_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.car_demo.car_demo.data.MechanicRepository;

@RunWith(MockitoJUnitRunner.class)
public class MechanicServiceTests {

    @Autowired
    MechanicRepository mechanicRepository;

    @Test
    public void getMechanicsFromInventory() {
        
    }
    
}
