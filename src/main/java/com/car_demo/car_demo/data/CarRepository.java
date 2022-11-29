//Jeremy Holloway
package com.car_demo.car_demo.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.car_demo.car_demo.definitions.Car;

// define car repository as an extension of the CRUD repostiry, saves me time writing basic functions
public interface CarRepository extends CrudRepository<Car, Long> {
    // define a find by id function with an optional for existence checking
    Optional<Car> findCarById(Long id);
}
