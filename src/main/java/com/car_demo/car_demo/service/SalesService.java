// Jeremy Holloway
package com.car_demo.car_demo.service;

import java.util.List;

import com.car_demo.car_demo.definitions.SalesEmployee;

// define a service interface to ensure basic functionality is present
public interface SalesService {
    
    /**
     * define a getter for sales employees
     * @param id Long value that correspondes to a specific employee
     * @return employee with the corresponding id value
     */
    SalesEmployee getEmployeeById(Long id);

    /**
     * define update function for sales employees
     * @param salesEmployee valid employee object to be updated
     * @return the updated sales employee
     */
    SalesEmployee updateEmployee(SalesEmployee salesEmployee);

    /**
     * define delete function for sales employees
     * @param id Long value that correspondes to a specific employee
     */
    void deleteEmployee(Long id);

    /**
     * define add function for sales employees
     * @param salesEmployee valid employee to be saved in the db
     */
    void addEmployee(SalesEmployee salesEmployee);

    /**
     * define a get all function for sales employees
     * @return a list of sales employees
     */
    List<SalesEmployee> getSalesEmployees();
}
