// Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.car_demo.car_demo.data.MechanicRepository;
import com.car_demo.car_demo.definitions.Mechanic;

@Service
public class MechanicImpl implements MechanicService {

    // define a mechanic repository, spring boot will wire it
    @Autowired
    MechanicRepository mechanicRepository;

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.MechanicService#getMechanicById(java.lang.Long)
     */
    @Override
    public Mechanic getMechanicById(Long id) {
        return unwrap_mechanic(mechanicRepository.findById(id));
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.MechanicService#updatMechanic(com.car_demo.car_demo.definitions.Mechanic)
     */
    @Override
    public Mechanic updateMechanic(@Valid Mechanic mechanic) {
        mechanicRepository.save(mechanic);
        return getMechanicById(mechanic.getId());
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.MechanicService#addEmployee(com.car_demo.car_demo.definitions.Mechanic)
     */
    @Override
    public void addEmployee(@Valid Mechanic mechanic) {
        mechanicRepository.save(mechanic);        
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.MechanicService#deleteEmployee(java.lang.Long)
     */
    @Override
    public void deleteEmployee(Long id) {
        mechanicRepository.delete(getMechanicById(id));
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.MechanicService#getMechanics()
     */
    @Override
    public List<Mechanic> getMechanics() {
        return (List<Mechanic>) mechanicRepository.findAll();
    }
    
    /**
     * this function will remove the optional wrapper from the mechanic object 
     * @param mechanic mechanic object with optional wrapper
     * @return mechanic object or throw a data integrity exception
     */
    static Mechanic unwrap_mechanic(Optional<Mechanic> mechanic) {
        // if mechanic object is present return it
        if(mechanic.isPresent()) return mechanic.get();
        // or else throw new data integrity violation
        else throw new DataIntegrityViolationException("Mechanic not present");
    }

}
