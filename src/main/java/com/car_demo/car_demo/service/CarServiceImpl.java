//Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.car_demo.car_demo.data.CarRepository;
import com.car_demo.car_demo.definitions.Car;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#getCarById(java.lang.Long)
     */
    @Override
    public Car getCarById(Long id) {
        return unwrap_car(carRepository.findCarById(id));
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#saveCar(com.car_demo.car_demo.definitions.Car)
     */
    @Override
    public void saveCar(Car car) {
        carRepository.save(car);       
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#getInventory()
     */
    @Override
    public List<Car> getInventory() {
        return (List<Car>) carRepository.findAll();
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#deleteCar(java.lang.Long)
     */
    @Override
    public void deleteCar(Long id) {
        carRepository.delete(getCarById(id));  
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#updateCar(com.car_demo.car_demo.definitions.Car)
     */
    @Override
    public Car updateCar(Car car) {
        carRepository.save(car);
        return getCarById(car.getId());
    }

    /**
     * @param car optional object to be returned if present
     * @return car object without optional wrapper
     */
    static Car unwrap_car(Optional<Car> car) {
        // if the car object is present return it
        if(car.isPresent()) return car.get();
        // or else throw a data integrity violation
        else throw new DataIntegrityViolationException("Car is not present");
    }
    
}
