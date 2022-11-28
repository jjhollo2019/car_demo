package com.car_demo.car_demo.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_demo.car_demo.data.CarRepository;
import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.exception.CarNotFoundException;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#getCarById(java.lang.String)
     */
    @Override
    public Car getCarById(String id) {
        return carRepository.getCar(findIndexById(id));
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#saveCar(com.car_demo.car_demo.definitions.Car)
     */
    @Override
    public void saveCar(Car car) {
        carRepository.addCar(car);        
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#getInventory()
     */
    @Override
    public List<Car> getInventory() {
        return carRepository.getInventory();
    }

    /**
     * @param id string identifier for specific car instance
     * @return car object with matching id or throw exception
     */
    private int findIndexById(String id) {
        return IntStream.range(0, carRepository.getInventory().size())
            .filter(index -> carRepository.getInventory().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new CarNotFoundException(id));
    }
    
}
