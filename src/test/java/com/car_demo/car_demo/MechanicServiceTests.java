package com.car_demo.car_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.car_demo.car_demo.data.MechanicRepository;
import com.car_demo.car_demo.definitions.Mechanic;
import com.car_demo.car_demo.service.MechanicImpl;
import com.car_demo.car_demo.service.MechanicService;

@RunWith(MockitoJUnitRunner.class)
public class MechanicServiceTests {

    @Mock
    private MechanicRepository mechanicRepository;

    @InjectMocks
    private MechanicService mechanicService = new MechanicImpl();

    /**
     * test for the find all function of the service class
     */
    @Test
    public void getMechanicsTest() {
        // create a list to return when the find all function is called
        List<Mechanic> mechanics = Arrays.asList(
            new Mechanic("John Smith", 35000),
            new Mechanic("Carl Klaus", 65000)
        );
        // when the underlying repository function is called return the list created above
        when(mechanicRepository.findAll()).thenReturn(mechanics);

        // call get all function from the service layer
        List<Mechanic> result = mechanicService.getMechanics();

        // assert the data matches our test set
        assertEquals("John Smith", result.get(0).getName());
        assertEquals(65000, result.get(1).getSalary());        
    }

    /**
     * test of return by id function
     */
    @Test
    public void returnMechanicByIdTest() {
        // create a mock mechanic object to return when the find function is called
        Optional<Mechanic> mMechanic = Optional.of(new Mechanic("John Smith", 156000));
        // when the repository layer find function is called return the mock mechanic object
        when(mechanicRepository.findById(mMechanic.get().getId())).thenReturn(mMechanic);

        // call the service layer function on the mock mechanic id
        Mechanic result = mechanicService.getMechanicById(mMechanic.get().getId());
        // assert the id values match between the result and the mock mechanic
        assertEquals(mMechanic.get().getId(), result.getId());
    }
    
}
