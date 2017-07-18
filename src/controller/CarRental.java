package controller;

import model.Car;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CarRental {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final HashMap<Car, CarSpec> carToCarSpecMap = new HashMap<>();
    private final HashMap<Customer, ArrayList<Car>> rentalListByCustomerMap = new HashMap<>();

    ArrayList<Car> carArrayList = new ArrayList<>();

 // When the CarRental controller is created, we are pre-filling the list with hardcoded item.
    public CarRental(){
        customerList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
    }

    public List<Customer> getCustomers(){
        return customerList;
    }


    // method to get car by
//    public CarSpec getCarByID(String id){
//        for (CarSpec carSpec : carArrayList) {
//            if(Objects.equals(carSpec.getModel(), id)){
//                return null;
//            }
//        }
//        return null;
//    }

    public HashMap<Car, CarSpec> getOutstandingRentalsForCustomer(Customer customer){
        if (rentalListByCustomerMap.containsKey(customer)){
            final HashMap<Car, CarSpec> rentedCarsAndSpecsMap = new HashMap<>();
            rentalListByCustomerMap.get(customer).forEach(car -> );
        }

        return null;
    }

}
