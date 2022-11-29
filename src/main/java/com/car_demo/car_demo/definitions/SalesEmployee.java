// Jeremy Holloway
package com.car_demo.car_demo.definitions;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/* This class will serve as the basic data type for all sales employees
 * I'm defining all of the data points about a sales employee in this class
 * 
 * I'm using lombok to generate the setters, getters, and constructors
 * it saves me time on writing them
 */

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
// define sales employee as an entity and a table
@Entity
@Table(name = "sales_employee")
public class SalesEmployee {

    // The id should be auto generated and designated as the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // every sales employee must have a name
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    // every employee must have a salary
    @NonNull
    @Column(name = "salary")
    private Integer salary;

    // not every sales person needs to have sold a car to exist
    @JsonIgnore
    @OneToMany(mappedBy = "sold_by", cascade = CascadeType.ALL)
    private Set<Car> cars_sold;
    
}
