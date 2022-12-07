// Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;

import com.car_demo.car_demo.definitions.Car;

//define some basic features for implementations
public interface CarService {

    /**
     * @param id string identifier for specific car instance
     * @return car object identified by id
     */
    Car getCarById(Long id);

    /**
     * @param car car object to be saved into db
     */
    void saveCar(Car car);

    /**
     * @param car car object to updated in the db
     * @return updated car object
     */
    Car updateCar(Car car);

    /**
     * @param id string identifier for specific car instance
     */
    void deleteCar(Long id);

    /**
     * @return all cars saved in the db
     */
    List<Car> getInventory();
    
    /**
     * @param carId
     * @param salesId
     */
    void addSalesEmployee(Long carId, Long salesId);
}
