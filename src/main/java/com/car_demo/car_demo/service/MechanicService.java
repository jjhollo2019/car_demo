// Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;

import com.car_demo.car_demo.definitions.Mechanic;

// define the service layer as an interface to ensure basic functions are present
public interface MechanicService {

    /**
     * get mechanic based on an id value
     * @param id Long number that represents a specific mechanic
     * @return corresponding mechanic object
     */
    Mechanic getMechanicById(Long id);

    /**
     * update mechanics by passing in a new mechanic object
     * @param mechanic object representing a mechanic
     * @return updated mechanic object
     */
    Mechanic updateMechanic(Mechanic mechanic);

    /**
     * add mechanic to the db
     * @param mechanic object representing a mechanic
     */
    void addEmployee(Mechanic mechanic);

    /**
     * delete a mechanic based on the id value
     * @param id Long number that represents a specific mechanic
     */
    void deleteEmployee(Long id);

    /**
     * get all mechanics in the db
     * @return all mechanics in the db
     */
    List<Mechanic> getMechanics();
}
