package com.car_demo.car_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    /**
     * test for the add function of the service layer
     */
    @Test
    public void addMechanicTest() {
        // create a mock mechanic object
        Mechanic mMechanic = new Mechanic("John Smith", 45000);
        // call save function from service class
        mechanicService.addEmployee(mMechanic);
        // verify the repository layer function is only called once
        verify(mechanicRepository, times(1)).save(mMechanic);
    }

    /**
     * test for the update function of the service layer
     */
    @Test
    public void updateMechanicTest() {
        // create a mock mechanic object
        Mechanic mMechanic = new Mechanic("John Smith", 45000);
        // when the find by id function is called at the repository layer return the mock mechanic
        when(mechanicRepository.findById(mMechanic.getId())).thenReturn(Optional.of(mMechanic));

        // call find function from the service layer to get mechanic object
        Mechanic mechanic = mechanicService.getMechanicById(mMechanic.getId());
        // set new salary
        mechanic.setSalary(65000);
        // use the update function to save the changes and retrieve the updated object
        Mechanic updatedMechanic = mechanicService.updateMechanic(mechanic);
        
        // assert the salary change we're expecting
        assertEquals(65000, updatedMechanic.getSalary());
    }
    
}
