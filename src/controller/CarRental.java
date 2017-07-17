package controller;

import model.Car;
import model.CarSpec;
import model.Customer;

import java.util.ArrayList;
import java.util.Objects;

public class CarRental {


    ArrayList<Customer> customerArrayList = new ArrayList<>();

    ArrayList<Car> carArrayList = new ArrayList<>();

 // When the CarRental controller is created, we are pre-filling the list with hardcoded item.
    public CarRental(){
        customerArrayList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerArrayList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerArrayList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));

        carArrayList.add(new Car("1234"));
        carArrayList.add(new Car("4321"));
        carArrayList.add(new Car("2468"));
        carArrayList.add(new Car("1357"));
        carArrayList.add(new Car("7777"));

    }


    // method to get car by
    public CarSpec getCarByID(String id){
        for (CarSpec carSpec : carArrayList) {
            if(Objects.equals(carSpec.getModel(), id)){
                return null;
            }
        }
        return null;
    }


}
