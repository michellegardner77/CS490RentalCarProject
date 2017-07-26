package DAL;

import core.DBManager;
import model.CarSizeEnum;
import model.CarSpec;
import model.Customer;
import model.Rental;
import model.RentalStatusEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalQueries {
    private DBManager dbManager;
    private String getCustomersQuery =
            "SELECT customer_name, customer_telephone, customer_address FROM rentaldb.customer";

    public RentalQueries() {
        this.dbManager = DBManager.getInstance();
    }

    public List<Customer> getAllCustomers() {
        final ArrayList customerList = new ArrayList();
        try {
            final ResultSet rs = dbManager.executeQuery(getCustomersQuery);
            while (rs.next()) {
                final String customerName = rs.getString("customer_name");
                final String customerPhone = rs.getString("customer_telephone");
                final String customerAddress = rs.getString("customer_address");

                final Customer customer = new Customer(customerName, customerPhone, customerAddress);
                customerList.add(customer);
            }
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
            return customerList;
        }
        return customerList;
    }

    public List<Rental> getRentedReturnedCars(String Status, String customerName) {
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "SELECT rent.car_id, spec.make, spec.model, spec.year, spec.size, rent.rent_status, rent.rent_date, rent.return_date FROM"
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

    private List<Rental> resultSetToRentalList(ResultSet carResultSet) {
        List<Rental> rentalList = new ArrayList<>();

        try {
            while (carResultSet.next()) {
                String carID = carResultSet.getString("car_id");

                RentalStatusEnum rentalStatus =
                        carResultSet.getString("rental_status").equals("returned") ? RentalStatusEnum.returned : RentalStatusEnum.loanedOut;

                String rentedDate = carResultSet.getString("rent_date");
                String returnDate = carResultSet.getString("return_date");

                Rental rental = new Rental(Calendar.getInstance(), Calendar.getInstance(), rentalStatus, carID);
                rentalList.add(rental);
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
                "SELECT rent.car_id, spec.make, spec.model, spec.year, spec.size, rent.rent_status, rent.rent_date, rent.return_date FROM"
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

        return resultSetToCarSpeclList(rs);
    }

    private List<CarSpec> resultSetToCarSpeclList(ResultSet carResultSet) {
        List<CarSpec> carSpecList = new ArrayList<>();

        try {
            while (carResultSet.next()) {
                String carID = carResultSet.getString("car_id");
                String carMake = carResultSet.getString("make");
                String carModel = carResultSet.getString("model");
                String carYear = carResultSet.getString("year");
                CarSizeEnum carSize;
                switch (carResultSet.getString("size")) {
                    case "small":
                        carSize = CarSizeEnum.small;
                        break;
                    case "large":
                        carSize = CarSizeEnum.large;
                        break;
                    default:
                        carSize = CarSizeEnum.midsize;
                }
                CarSpec carspec = new CarSpec(carID, carMake, carModel, Year.now(), carSize);
                carSpecList.add(carspec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return carSpecList;
        }
        return carSpecList;
    }


    //Updates db to "rent" or "return" a car changing status to loanedOut or returned
    public void rentReturnSelectedCar(String carId, String Status, Date rentDate, Date returnDate){
        PreparedStatement stmt;
        ResultSet rs;

        String query =
                "UPDATE rentaldb.rental SET rental_status = ?, rent_date = ?, return_date = ? WHERE car_id =? ";

        try{
            stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, Status);
//            stmt.setString(2,rentDate);
//            stmt.setString(3,returnDate);
            stmt.setString(4, carId);

            stmt.executeUpdate();
            return;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }





}



