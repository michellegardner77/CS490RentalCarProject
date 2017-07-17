package controller;

import model.Car;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
=======
import java.util.Objects;
>>>>>>> 633c306637e2efa2f3fd263cf5e724d4e7706d7e

public class CarRental {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final HashMap<Car, CarSpec> carToCarSpecMap = new HashMap<>();
    private final HashMap<Customer, ArrayList<Car>> rentalListByCustomerMap = new HashMap<>();

    ArrayList<Car> carArrayList = new ArrayList<>();

 // When the CarRental controller is created, we are pre-filling the list with hardcoded item.
    public CarRental(){
<<<<<<< HEAD
        customerList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
    }

    public List<Customer> getCustomers(){
        return customerList;
=======
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
>>>>>>> 633c306637e2efa2f3fd263cf5e724d4e7706d7e
    }

    public HashMap<Car, CarSpec> getOutstandingRentalsForCustomer(String name){
        if (rentalListByCustomerMap.containsKey(name)){
            //final HashMap<Car, CarSpec>
        }

        return null;
    }

}
