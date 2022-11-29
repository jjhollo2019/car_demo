// Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;

import com.car_demo.car_demo.data.SalesEmployeeRepository;
import com.car_demo.car_demo.definitions.SalesEmployee;

// implement the service interface defined in SalesService
public class SalesImpl implements SalesService {

    // spring boot will wire this to the sales employee repo
    SalesEmployeeRepository salesEmployeeRepository;

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.SalesService#getEmployeeById(java.lang.Long)
     */
    @Override
    public SalesEmployee getEmployeeById(Long id) {
        return unwrap_employee(salesEmployeeRepository.findById(id));
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.SalesService#updateEmployee(com.car_demo.car_demo.definitions.SalesEmployee)
     */
    @Override
    public SalesEmployee updateEmployee(SalesEmployee salesEmployee) {
        salesEmployeeRepository.save(salesEmployee);
        return getEmployeeById(salesEmployee.getId());
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.SalesService#deleteEmployee(java.lang.Long)
     */
    @Override
    public void deleteEmployee(Long id) {
        salesEmployeeRepository.delete(getEmployeeById(id));        
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.SalesService#addEmployee(com.car_demo.car_demo.definitions.SalesEmployee)
     */
    @Override
    public void addEmployee(SalesEmployee salesEmployee) {
        salesEmployeeRepository.save(salesEmployee);        
    }

    /* (non-Javadoc)
     * @see com.car_demo.car_demo.service.SalesService#getSalesEmployees()
     */
    @Override
    public Set<SalesEmployee> getSalesEmployees() {
        return (Set<SalesEmployee>) salesEmployeeRepository.findAll();
    }

    /**
     * This function unwraps an optional sales employee or throws a violation
     * @param employee optional employee for possible return if present
     * @return the employee or throw a data violation exception
     */
    static SalesEmployee unwrap_employee(Optional<SalesEmployee> employee) {
        if(employee.isPresent()) return employee.get();
        else throw new DataIntegrityViolationException("Employee not present");
    }
    
}
