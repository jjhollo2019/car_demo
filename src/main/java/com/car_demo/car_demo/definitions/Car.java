// Jeremy Holloway
package com.car_demo.car_demo.definitions;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/* This class will serve as the basic data type for all cars
 * I'm defining all of the data points about a car in this class
 * 
 * I'm using lombok to generate the setters, getters, and constructors
 * it saves me time on writing them
 */

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
// define an entity and table for car
@Entity
@Table(name = "car")
public class Car {
    
    //randomly generated id value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    // every car must have a make
    @NotBlank(message = "make cannot be blank")
    @NonNull
    @Column(name = "make", nullable = false)
    private String make;
    
    // every car must have a model
    @NotBlank(message = "model cannot be blank")
    @NonNull
    @Column(name = "model", nullable = false)
    private String model;

    // every car must have a production year
    @NotBlank(message = "production year cannot be blank")
    @NonNull
    @Column(name = "production_year", nullable = false)
    private String production_year;

    // every car must have a mileage
    @Min(value = 1, message = "mileage must be greater than zero")
    @NonNull
    @Column(name = "mileage")
    private Integer mileage;

    // not every car needs a mechanic to exist
    @JsonIgnore
    @ManyToMany(mappedBy = "works_on")
    private Set<Mechanic> works_on;

    // not every car needs a sales employee to exist
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private SalesEmployee sold_by;
}
