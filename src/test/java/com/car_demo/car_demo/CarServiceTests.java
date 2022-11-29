/* Jeremy Holloway
 * These are basic unit tests for the service layer implementation
 * I want to verify the CRUD operations work
 */

package com.car_demo.car_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.car_demo.car_demo.data.CarRepository;
import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.service.CarService;
import com.car_demo.car_demo.service.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTests {

    //mock up the repository layer
    @Mock
    private CarRepository carRepository;

    //for now I'm injecting the List implementation
    @InjectMocks
    private CarService carService = new CarServiceImpl();

    /**
     * test for the get inventory function of the service layer
     */
    @Test
    public void getCarsFromRepositoryTest() {
        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car("1", "dodge", "ram", "2020", 27000));
        cars.add(new Car("2", "dodge", "dart", "2013", 152000));
        when(carRepository.getInventory()).thenReturn(cars);

        List<Car> result = carService.getInventory();

        assertEquals("dodge", result.get(0).getMake());
        assertEquals("dart", result.get(1).getModel());
    }

    /**
     * test for the get car by id function of the service layer
     */
    @Test
    public void returnCarByIdTest() {
        // create an initial car object
        Car mock_car = new Car("1", "dodge", "ram", "2020", 27000);
        // when the inventory is called return the mock car
        when(carRepository.getInventory()).thenReturn(Arrays.asList(mock_car));
        // when the index of that itme is called return the mock car object
        when(carRepository.getCar(0)).thenReturn(mock_car);

        // get the mock car by it's id
        Car result = carService.getCarById("1");
        //assert the id from the mock matches
        assertEquals(mock_car.getId(), result.getId());
    }

    /**
     * test for the add function of the service layer
     */
    @Test
    public void addCarTest() {
        // create a new car object
        Car new_car = new Car("2", "dodge", "dart", "2013", 156000);
        // use the service layer to add a new car to the inventory
        carService.saveCar(new_car);
        // verify the repository layer was only called once
        verify(carRepository, times(1)).addCar(new_car);
    }

    /**
     * test for the update function of the service layer
     */
    @Test
    public void updateCarTest() {
        // create an initial car object
        Car mock_car = new Car("1", "dodge", "ram", "2020", 27000);
        // when the inventory is called return the mock car
        when(carRepository.getInventory()).thenReturn(Arrays.asList(mock_car));
        // when the index of that itme is called return the mock car object
        when(carRepository.getCar(0)).thenReturn(mock_car);

        // use the service to get the car as an object
        Car car = carService.getCarById("1");
        // set new year and mileage
        car.setYear("2022");
        car.setMileage(20);
        // call the update function from the service layer 
        Car updated_car = carService.updateCar(car);
        
        //check the values we set in the returned object from the service layer
        assertEquals("2022", updated_car.getYear());
        assertEquals(20, updated_car.getMileage());
    }
    
}
