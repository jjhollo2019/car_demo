package com.car_demo.car_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.car_demo.car_demo.data.SalesEmployeeRepository;
import com.car_demo.car_demo.definitions.SalesEmployee;
import com.car_demo.car_demo.service.SalesImpl;
import com.car_demo.car_demo.service.SalesService;

@RunWith(MockitoJUnitRunner.class)
public class SalesServiceTests {

    @Mock
    private SalesEmployeeRepository salesEmployeeRepository;

    @InjectMocks
    private SalesService salesService = new SalesImpl();

    /**
     * test for find all function of the service layer
     */
    @Test
    public void getSalesEmployeesTest() {
        // create a list of mock employees for testing
        List<SalesEmployee> mEmployees = Arrays.asList(
            new SalesEmployee("John Smith", 37000),
            new SalesEmployee("Donald Blythe", 32000)
        );
        // when the repository find all function is called return the mock list of employees
        when(salesEmployeeRepository.findAll()).thenReturn(mEmployees);

        // call service layer function to return sales employees
        List<SalesEmployee> result = salesService.getSalesEmployees();

        // assert mock objects are in the result
        assertEquals("John Smith", result.get(0).getName());
        assertEquals(32000, result.get(1).getSalary());
    }

    /**
     * test of find by id function at the service layer
     */
    @Test
    public void returnSalesEmployeeByIdTest() {
        // create mock sales employee 
        SalesEmployee mSalesEmployee = new SalesEmployee("Donald Blythe", 45000);
        // when the find id function is called at the repository layer return the mock sales employee
        when(salesEmployeeRepository.findById(mSalesEmployee.getId())).thenReturn(Optional.of(mSalesEmployee));

        // get the mock sales employee by calling the find function at the service layer
        SalesEmployee result = salesService.getEmployeeById(mSalesEmployee.getId());

        // assert the id between the mock object and the object returned by the service layer
        assertEquals(mSalesEmployee.getId(), result.getId());
    }

    /**
     * test of add function at the service layer
     */
    @Test
    public void addSalesEmployeeTest() {
        // create a mock sales employee to add
        SalesEmployee mSalesEmployee = new SalesEmployee("Donald Blythe", 450000);
        // use the service layer function to save the mock employee
        salesService.addEmployee(mSalesEmployee);

        // verify the repository layer save function is only called once
        verify(salesEmployeeRepository, times(1)).save(mSalesEmployee);
    }

    @Test
    public void updateSalesEmployeeTest() {
        // create a mock sales employee
        SalesEmployee mSalesEmployee = new SalesEmployee("Donald Blythe", 65000);
        // when the find by id function is called at the repository layer return the mock sales employee object
        when(salesEmployeeRepository.findById(mSalesEmployee.getId())).thenReturn(Optional.of(mSalesEmployee));

        // use the service layer to retrieve the mock employee object
        SalesEmployee salesEmployee = salesService.getEmployeeById(mSalesEmployee.getId());
        // set the new salary
        salesEmployee.setSalary(75000);
        // call the update function of the service layer
        SalesEmployee result = salesService.updateEmployee(salesEmployee);

        assertEquals(75000, result.getSalary());
    }
        
}
