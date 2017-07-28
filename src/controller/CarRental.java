package controller;

import model.Car;
import model.CarSizeEnum;
import model.CarSpec;
import model.Customer;
import model.Rental;
import utillity.CarNotFoundException;
import core.DBManager;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CarRental {
    public enum SelectedTab {
        find_car, outstanding, returned
    }


    private DBManager dbManager;
    private static CarRental singletonInstance = null;
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final HashMap<Customer, ArrayList<Rental>> rentalListByCustomerMap = new HashMap<>();
    private final HashMap<Customer, ArrayList<Rental>> previousRentalListByCustomerMap = new HashMap<>();
    private final HashMap<Car, Rental> rentalMapByCar = new HashMap<>();
    private final HashMap<Car, CarSpec> carSpecByCar = new HashMap<>();
    private final List<Car> carList = new ArrayList();
    HashSet<Car> rentedSet = new HashSet<>();
    HashSet<Car> availableSet = new HashSet<>();

    private static Customer currentCustomer = null;
    private static SelectedTab currentTab = null;

    // When the CarRental controller is created, we are pre-filling the list with hardcoded item.
    private CarRental() {
        if (customerList.isEmpty()) {
            customerList.add(new Customer("Jennifer", "816-999-9999", "123 Internet St."));
            customerList.add(new Customer("Michelle", "816-777-7777", "707 Buster Ave."));
            customerList.add(new Customer("Jack", "816-444-4444", "131 NW Nibbles Lane"));
        }

        if (carList.isEmpty()) {
            carList.add(new Car("sheila"));
            carList.add(new Car("dingo"));
            carList.add(new Car("cassowary"));

            carSpecByCar.put(carList.get(0), new CarSpec("sheila", "subaru", "impresa", Year.now(), CarSizeEnum.midsize));
            carSpecByCar.put(carList.get(1), new CarSpec("dingo", "ford", "focus", Year.now(), CarSizeEnum.midsize));
            carSpecByCar.put(carList.get(2), new CarSpec("cassowary", "ram", "truck", Year.now(), CarSizeEnum.large));

            availableSet.addAll(carList);
        }
    }


    public static CarRental getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new CarRental();
            return singletonInstance;
        }
        return singletonInstance;
    }

    public void setDBManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer customer) {
        if (customerList.contains(customer)) {
            currentCustomer = customer;
            return;
        }
        throw new RuntimeException();
    }


    public void addCustomer(final String name, final String phoneNumber, final String address) {
        final Customer customer = new Customer(name, phoneNumber, address);
        customerList.add(customer);
        rentalListByCustomerMap.put(customer, new ArrayList<>());
    }


    public List<CarSpec> getAvailableCars() {
        final List<CarSpec> availableList = new ArrayList<>();
        availableSet.forEach(car -> {
            availableList.add(carSpecByCar.get(car));
        });
        return availableList;
    }

    public void rentCarForCustomer(final Customer customer, final Car car) throws CarNotFoundException {
        if (!rentedSet.contains(car)) {
            rentalListByCustomerMap.putIfAbsent(customer, new ArrayList<>());
            rentalListByCustomerMap.get(customer).add(rentalMapByCar.get(car));

            rentedSet.add(car);
            availableSet.remove(car);
        } else {
            throw new CarNotFoundException("Car already rented!", car.getId());
        }
    }

    public void returnCarRentalForCustomer(final Customer customer, final Car car) throws CarNotFoundException {
        if (rentedSet.contains(car)) {
            rentalListByCustomerMap.get(customer).remove(car);
            rentedSet.remove(car);
            availableSet.add(car);
            previousRentalListByCustomerMap.putIfAbsent(customer, new ArrayList<>());
            previousRentalListByCustomerMap.get(customer).add(rentalMapByCar.get(car));
        } else {
            throw new CarNotFoundException("Car not rented out!", car.getId());
        }
    }

    public ArrayList<Rental> getOutstandingRentalsForCustomer(final Customer customer) {
        ArrayList<Rental> rentals = new ArrayList<>();
        rentalListByCustomerMap.get(customer).forEach(car -> {
            if (car != null) {
                rentals.add(rentalMapByCar.get(car));
            }
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

    public ArrayList<Rental> getPreviousRentalsForCustomer(final Customer customer) {
        return previousRentalListByCustomerMap.get(customer);
    }

    public SelectedTab getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(SelectedTab currentTab) {
        CarRental.currentTab = currentTab;
    }
}
