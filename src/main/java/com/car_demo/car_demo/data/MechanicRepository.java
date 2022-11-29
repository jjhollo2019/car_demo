// Jeremy Holloway
package com.car_demo.car_demo.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.car_demo.car_demo.definitions.Mechanic;

// define the mechanic repository as an extension of CRUD Respository, saves me time writing basic functions
public interface MechanicRepository extends CrudRepository<Mechanic, Long> {
    // define a find by id function
    Optional<Mechanic> findMechanicById(Long id);
}
