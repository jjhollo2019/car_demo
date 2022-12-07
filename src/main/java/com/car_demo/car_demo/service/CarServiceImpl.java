//Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.car_demo.car_demo.data.CarRepository;
import com.car_demo.car_demo.data.SalesEmployeeRepository;
import com.car_demo.car_demo.definitions.Car;
import com.car_demo.car_demo.definitions.SalesEmployee;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private SalesEmployeeRepository salesEmployeeRepository;

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

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.CarService#addSalesEmployee(java.lang.Long, java.lang.Long)
     */
    @Override
    public void addSalesEmployee(Long carId, Long salesId) {
        // get matching car object
        Car car = getCarById(carId);
        // declare a sales employee object 
        SalesEmployee salesEmployee;
        // retrieve matching sales object in optional wrapper
        Optional<SalesEmployee> optionalEmployee = salesEmployeeRepository.findSalesEmployeeById(salesId);
        // check if object is present
        if(optionalEmployee.isPresent()){
            // initialize sales employee object to retireved object
            salesEmployee = optionalEmployee.get();
        }
        // throw data integrity violation if sales employee not found
        else throw new DataIntegrityViolationException("No sales employee with matching id");

        // set the sales employee as the sold by relationship with car object
        car.setSold_by(salesEmployee);
        // save the updated object in the db
        carRepository.save(car);
    }
    
}
