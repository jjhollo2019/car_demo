// Jeremy Holloway
package com.car_demo.car_demo.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.car_demo.car_demo.definitions.SalesEmployee;

// define sales employee repository as an extension of CRUD repository to save time writing basic functions
public interface SalesEmployeeRepository extends CrudRepository<SalesEmployee, Long> {
    // define a find by id function with an optional for existence checking
    Optional<SalesEmployee> findSalesEmployeeById(Long id);
}
