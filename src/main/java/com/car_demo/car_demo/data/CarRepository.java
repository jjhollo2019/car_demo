package com.car_demo.car_demo.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.car_demo.car_demo.definitions.Car;

@Repository
public class CarRepository {

    // I'm using a list for now, will replace with h2 table
    private List<Car> inventory = new ArrayList<Car>();

    
    /** getCar
     * @param index integer index in inventory list
     * @return car object at the specified index
     */
    public Car getCar(int index) {
        // call get operation on array list
        return inventory.get(index);
    }


    /** addCar
     * @param car car object to be added to the inventory
     */
    public void addCar(Car car) {
        // call add operation on array list
        inventory.add(car);
    }

    /** updateCar
     * @param index integer index in inventory list
     * @param car updated car object to be added to the inventory
     */
    public void updateCar(int index, Car car) {
        // call set operation on array list
        inventory.set(index, car);
    }

    /** removeCar
     * @param index integer index in inventory list
     */
    public void removeCar(int index) {
        // call remove operation on array list
        inventory.remove(index);
    }

    // getter for inventory
    public List<Car> getInventory() {
        return inventory;
    }
    
}
