package com.car_demo.car_demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_demo.car_demo.definitions.SalesEmployee;
import com.car_demo.car_demo.service.SalesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Sales Employee Controller", description = "CRUD operations for sales employees")
@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class SalesEmployeeController {

    // create private instance of sales employee service
    private SalesService salesService;

    /**
     * @return list of all employees from the repository
     */
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all sales employees", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class))))
    @Operation(summary = "Retrieves sales employees", description = "Provides a list of all sales employees")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalesEmployee>> getSalesEmployees() {
        // return the list from the repository layer and http code 200 (ok)
        return new ResponseEntity<>(salesService.getSalesEmployees(), HttpStatus.OK);
    }

    /**
     * @param id Long unique identifier for sales employees
     * @return corresponding sales employee object and http code 200 (ok)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class)))),
        @ApiResponse(responseCode = "404", description = "Sales employee does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class))))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalesEmployee> getEmployeeById(@PathVariable Long id) {
        // return the sales employee object with the matching id and http status code 200 (ok)
        return new ResponseEntity<SalesEmployee>(salesService.getEmployeeById(id), HttpStatus.OK);
    }

    /**
     * @param salesEmployee valid sales employee object
     * @return http status code 201 (created)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful creation of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful creation of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class))))
    })
    @PostMapping
    public ResponseEntity<HttpStatus> addSalesEmployee(@Valid @RequestBody SalesEmployee salesEmployee) {
        salesService.addEmployee(salesEmployee);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    /**
     * @param salesEmployee valid sales employee object
     * @return http status code 202 (accepted)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful update of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful update of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class))))
    })
    @PutMapping
    public ResponseEntity<SalesEmployee> updateSalesEmployee(@Valid @RequestBody SalesEmployee salesEmployee) {
        // call service layer function to store object
        salesService.updateEmployee(salesEmployee);
        // call service layer to retrieve updated object and return it to user with http code 202 (accepted)
        return new ResponseEntity<>(salesService.getEmployeeById(salesEmployee.getId()), HttpStatus.ACCEPTED);
    }

    /**
     * @param id Long unique identifier for sales employees
     * @return http code 204 (no content)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful deletion of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class)))),
        @ApiResponse(responseCode = "404", description = "Unsuccessful deletion of sales employee", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SalesEmployee.class))))
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteSalesEmployee(@PathVariable Long id) {
        // call service layer delete function
        salesService.deleteEmployee(id);
        // return http status code 204 (no content)
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
    
}
