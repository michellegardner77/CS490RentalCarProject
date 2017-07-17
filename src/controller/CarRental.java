package controller;

import model.Customer;

import java.util.ArrayList;

public class CarRental {


    ArrayList<Customer> customerArrayList = new ArrayList<>();

 // When the CarRental is controller is created, we are pre-filling the list with hardcoded item.
    public CarRental(){
        customerArrayList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerArrayList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerArrayList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
    }


}
