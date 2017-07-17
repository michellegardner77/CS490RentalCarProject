package controller;

import model.Car;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarRental {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final HashMap<Car, CarSpec> carToCarSpecMap = new HashMap<>();
    private final HashMap<Customer, ArrayList<Car>> rentalListByCustomerMap = new HashMap<>();

 // When the CarRental is controller is created, we are pre-filling the list with hardcoded item.
    public CarRental(){
        customerList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
    }

    public List<Customer> getCustomers(){
        return customerList;
    }

    public HashMap<Car, CarSpec> getOutstandingRentalsForCustomer(String name){
        if (rentalListByCustomerMap.containsKey(name)){
            //final HashMap<Car, CarSpec>
        }

        return null;
    }

}
