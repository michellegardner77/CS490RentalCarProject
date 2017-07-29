package DAL;

import core.DBManager;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalQueries {
    private DBManager dbManager;

    public RentalQueries() {
        this.dbManager = DBManager.getInstance();
    }

    public List<Customer> getAllCustomers() {

        String getCustomersQuery =
                "SELECT customer_name, customer_phone, customer_address FROM rentaldb.customer";

        final ArrayList customerList = new ArrayList();

        ResultSet rs;
        try {
            rs = dbManager.executeQuery(getCustomersQuery);
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
            return customerList;
        }
        return resultSetToCustomerList(rs);
    }

    public List<Customer> searchCustomers(String searchString) {

        String getCustomersQuery =
                "SELECT customer_name, customer_phone, customer_address FROM rentaldb.customer " +
                        "where customer_name like '%" + searchString + "%' " +
                        "or customer_phone like '%" + searchString + "%' " +
                        "or customer_address like '%" + searchString + "%' ";

        final ArrayList customerList = new ArrayList();

        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = dbManager.getConnection().prepareStatement(getCustomersQuery);
            rs = stmt.executeQuery();
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
            return customerList;
        }
        return resultSetToCustomerList(rs);
    }

    private List<Customer> resultSetToCustomerList(ResultSet customerResultsList) {
        List<Customer> customerList = new ArrayList<>();

        try {
            while (customerResultsList.next()) {
                final String customerName = customerResultsList.getString("customer_name");
                final String customerPhone = customerResultsList.getString("customer_phone");
                final String customerAddress = customerResultsList.getString("customer_address");

                final Customer customer = new Customer(customerName, customerPhone, customerAddress);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return customerList;
        }
        return customerList;
    }



    public List<CarSpecRentalJoin> getRentedReturnedCars(String Status, String customerName) {
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "SELECT DISTINCT rent.car_id, spec.make, spec.model, spec.year, spec.size, rent.rent_status, rent.rent_date, rent.return_date FROM"
                        + " rentaldb.rental rent INNER JOIN rentaldb.carspec spec ON rent.car_id = spec.car_id "
                        + " WHERE rent.rental_status = ? AND rent.customer_name =?";
        try {
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, Status);
            stmt.setString(2, customerName);

            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return resultSetToRentalList(rs);
    }

    private List<CarSpecRentalJoin> resultSetToRentalList(ResultSet carResultSet) {
        List<CarSpecRentalJoin> rentalList = new ArrayList<>();

        try {
            while (carResultSet.next()) {
                String carID = carResultSet.getString("car_id");
                String make = carResultSet.getString("make");
                String model = carResultSet.getString("model");

                RentalStatusEnum rentalStatus =
                        carResultSet.getString("rental_status").equals("returned") ? RentalStatusEnum.returned : RentalStatusEnum.loanedOut;

                Date rentedDate = carResultSet.getDate("rent_date");
                Date returnDate = carResultSet.getDate("return_date");

                CarSpecRentalJoin carSpecRentalJoin= new CarSpecRentalJoin(carID, make, model, rentedDate, returnDate);
                rentalList.add(carSpecRentalJoin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return rentalList;
        }
        return rentalList;
    }

    public List<CarSpec> getCarSpecs(String Status) {
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "SELECT DISTINCT rent.car_id, spec.make, spec.model, spec.year, spec.size, rent.rental_status, rent.rent_date, rent.return_date FROM"
                        + " rentaldb.rental rent INNER JOIN rentaldb.carspec spec ON rent.car_id = spec.car_id "
                        + "WHERE rent.rental_status = ?";
        try {
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, Status);

            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return resultSetToCarSpecList(rs);
    }

    public List<CarSpec> searchCarSpecs(String Status, String searchString) {
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "SELECT DISTINCT rent.car_id, spec.make, spec.model, spec.year, spec.size FROM"
                        + " rentaldb.rental rent INNER JOIN rentaldb.carspec spec ON rent.car_id = spec.car_id "
                        + "WHERE rent.rental_status = ? AND "
                        + "(rent.car_id LIKE '%"+searchString+"%' "
                        + "OR spec.make LIKE '%"+searchString+"%' "
                        + "OR spec.model LIKE '%"+searchString+"%' "
                        + "OR spec.year LIKE '%"+searchString+"%' "
                        + "OR spec.size LIKE '%"+searchString+"%')";
        try {
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, Status);

            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return resultSetToCarSpecList(rs);
    }

    private List<CarSpec> resultSetToCarSpecList(ResultSet carResultSet) {
        List<CarSpec> carSpecList = new ArrayList<>();

        try {
            while (carResultSet.next()) {
                String carID = carResultSet.getString("car_id");
                String carMake = carResultSet.getString("make");
                String carModel = carResultSet.getString("model");
                String carYear = carResultSet.getString("year");
                CarSizeEnum carSize;
                String rsCarSize = carResultSet.getString("size");
                // Deal with null car sizes
                if (rsCarSize != null) {
                    switch (carResultSet.getString("size")) {
                        case "SMALL":
                            carSize = CarSizeEnum.small;
                            break;
                        case "LARGE":
                            carSize = CarSizeEnum.large;
                            break;
                        default:
                            carSize = CarSizeEnum.midsize;
                    }
                } else
                {
                    carSize = CarSizeEnum.midsize;
                }
                CarSpec carspec = new CarSpec(carID, carMake, carModel, carYear, carSize);
                carSpecList.add(carspec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return carSpecList;
        }
        return carSpecList;
    }


    //Updates db to "rent" or "return" a car changing status to loanedOut or returned
    public void rentSelectedCar(String carId, LocalDate rentDate){
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "UPDATE rentaldb.rental SET rental_status = ?, rent_date = ? WHERE car_id =? ";

        try{
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, "loanedOut");
            stmt.setDate(2, java.sql.Date.valueOf(rentDate));
            stmt.setString(3, carId);

            stmt.executeUpdate();
            return;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }

    //Updates db to "rent" or "return" a car changing status to loanedOut or returned
    public void returnSelectedCar(String carId, Date returnDate){
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "UPDATE rentaldb.rental SET rental_status = ?, return_date = ? WHERE car_id =? ";

        try{
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, "returned");
            stmt.setDate(2, (java.sql.Date) returnDate);
            stmt.setString(3, carId);

            stmt.executeUpdate();
            return;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }





}



