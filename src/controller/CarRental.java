package controller;

import model.Car;
import model.CarNotFoundException;
import model.CarSpec;
import model.Customer;
import model.Rental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CarRental {
    private DBManager dbManager;
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final HashMap<Customer, ArrayList<Rental>> rentalListByCustomerMap = new HashMap<>();
    private final HashMap<Car, Rental> rentalMapByCar = new HashMap<>();
    HashSet<Car> rentedSet = new HashSet<>();
    HashSet<Car> availableSet = new HashSet<>();

    // When the CarRental controller is created, we are pre-filling the list with hardcoded item.
    public CarRental() {
        customerList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
        customerList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
        customerList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
    }

    public void setDBManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public void addCustomer(final String name, final String phoneNumber, final String address) {
        final Customer customer = new Customer(name, phoneNumber, address);
        rentalListByCustomerMap.put(customer, new ArrayList<>());
    }

    public void rentCarForCustomer(final Customer customer, final Car car) {
        if (!rentedSet.contains(car)) {
            rentalListByCustomerMap.get(customer).add(rentalMapByCar.get(car));
            rentedSet.add(car);
            availableSet.remove(car);
        } else {
            throw new CarNotFoundException("Car already rented!", car.getId());
        }
    }

    public void returnCarRentalForCustomer(final Customer customer, final Car car) {
        if (rentedSet.contains(car)) {
            rentalListByCustomerMap.get(customer).remove(car);
            rentedSet.remove(car);
            availableSet.add(car);
        } else {
            throw new CarNotFoundException("Car not rented out!", car.getId());
        }
    }

    public ArrayList<Rental> getOutstandingRentalsForCustomer(final Customer customer) {
        ArrayList<Rental> rentals = new ArrayList<>();
        rentalListByCustomerMap.get(customer).forEach(car -> {
            rentals.add(rentalMapByCar.get(car));
        });

        return rentals;
    }

    public ArrayList<Customer> getCustomerContainingSubstring(final String substring) {
        final ArrayList<Customer> matchingCustomers = new ArrayList<>();
        customerList.forEach(customer -> {
            if (customer.contains(substring)) {
                matchingCustomers.add(customer);
            }
        });
        return matchingCustomers;
    }

    public ArrayList<Rental> getRentalContainingSubstringForCustomer(final String substring, final Customer customer) {
        final ArrayList<Rental> matchingRentals = new ArrayList<>();

        final ArrayList<Rental> rentalsForCustomer = rentalListByCustomerMap.get(customer);
        rentalsForCustomer.forEach(rental -> {
            if (rental.contains(substring)) {
                matchingRentals.add(rental);
            }
        });
        return matchingRentals;
    }
}
