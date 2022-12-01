//Jeremy Holloway
package com.car_demo.car_demo.definitions;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/* This class will serve as the basic data type for all mechanics
 * I'm defining all of the data points about a mechanic in this class
 * 
 * I'm using lombok to generate the setters, getters, and constructors
 * it saves me time on writing them
 */

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
// establish a mechanic entity and table name
@Entity
@Table(name = "mechanic")
public class Mechanic {

    // I want this auto generated and designated as the id field 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // every mechanic must have a name
    @NotBlank(message = "name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    // every employee must have a salary
    @Min(value = 1, message = "salary must be greater than zero")
    @NonNull
    @Column(name = "salary", nullable = false)
    private Integer salary;

    // not every mechanic needs to have cars they worked on, at least for them to exist
    @JsonIgnore
    @ManyToMany()
    @JoinTable( 
        name = "mechanic_car",
        joinColumns = @JoinColumn(name = "mechanic_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    private Set<Car> works_on;
    
}
