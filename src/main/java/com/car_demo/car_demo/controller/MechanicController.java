// Jeremy Holloway
package com.car_demo.car_demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_demo.car_demo.definitions.Mechanic;
import com.car_demo.car_demo.service.MechanicService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Mechanic Controller", description = "CRUD operations for mechanics")
@AllArgsConstructor
@RestController
// define the mechanic 
@RequestMapping("/mechanic")
public class MechanicController {

    // create a private instance of the service class
    MechanicService mechanicService;

    /**
     * @param id Long number unique to mechanics
     * @return corresponding mechanic object 
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class)))),
        @ApiResponse(responseCode = "404", description = "Mechanic does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getMechanic(@PathVariable Long id) {
        // call find function and return http code 200 (ok)
        return new ResponseEntity<Mechanic>(mechanicService.getMechanicById(id), HttpStatus.OK);
    }

    /**
     * @return all mechanics stored in the db
     */
    @ApiResponse(responseCode = "200", description = "Successful retrieval of mechanics", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Mechanic>> getMechanics() {
        // call find all function and return http code 200 (ok)
        return new ResponseEntity<>(mechanicService.getMechanics(), HttpStatus.OK);
    }

    /**
     * @param mechanic object to be added to the db
     * @return http code 201 (created)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful creation of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful creation of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    })
    @PostMapping
    public ResponseEntity<HttpStatus> saveMechanic(@Valid @RequestBody Mechanic mechanic) {
        // call add function from service class
        mechanicService.addEmployee(mechanic);
        // return http code 201 (created)
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    /**
     * @param mechanicId Long number correspondes to a unique mechanic entry
     * @param carId Long number corresponds to a unique car entry
     * @return http status code 202 (accepted)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful creation of mechanic and car relationship", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful creation of mechanic and car relationship", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    })
    @PostMapping("/{mechanicId}/car/{carId}")
    public ResponseEntity<HttpStatus> addWorksOn(@PathVariable Long mechanicId, @PathVariable Long carId) {
        mechanicService.addCar(mechanicId, carId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * @param mechanic object to be updated in the db
     * @return the mechanic object stored in the db and http code 202 (accepted)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful update of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful update of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    })
    @PutMapping
    public ResponseEntity<Mechanic> updateMechanic(@Valid @RequestBody Mechanic mechanic, BindingResult result) {
        if(result.hasErrors()) throw new DataIntegrityViolationException(result.toString());
        // call save function from service class
        mechanicService.updateMechanic(mechanic);
        // call retrieve function using the id from the mechanic object and http code 202 (accepted)
        return new ResponseEntity<Mechanic>(mechanicService.getMechanicById(mechanic.getId()), HttpStatus.ACCEPTED);
    } 
    
    /**
     * @param id Long number unique to mechanics
     * @return http code 204 (no content)
     */
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful deletion of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class)))),
        @ApiResponse(responseCode = "400", description = "Unsuccessful deletion of mechanic", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Mechanic.class))))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMechanic(@PathVariable Long id) {
        // call deletion function from service class
        mechanicService.deleteEmployee(id);
        // return http code 204 (no content)
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
    
}
