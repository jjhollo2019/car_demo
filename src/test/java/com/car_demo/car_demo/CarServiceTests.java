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

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService = new CarServiceImpl();

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

    @Test
    public void returnCarByIdTest() {
        Car mock_car = new Car("1", "dodge", "ram", "2020", 27000);
        when(carRepository.getInventory()).thenReturn(Arrays.asList(mock_car));
        when(carRepository.getCar(0)).thenReturn(mock_car);

        String id = mock_car.getId();
        Car result = carService.getCarById(id);

        assertEquals(mock_car.getId(), result.getId());
    }

    @Test
    public void addCarTest() {
        Car mock_car = new Car("1", "dodge", "ram", "2020", 27000);
        when(carRepository.getInventory()).thenReturn(Arrays.asList(mock_car));
        when(carRepository.getCar(0)).thenReturn(mock_car);

        Car new_car = new Car("2", "dodge", "dart", "2013", 156000);
        carService.saveCar(new_car);

        verify(carRepository, times(1)).addCar(new_car);
    }
    
}
