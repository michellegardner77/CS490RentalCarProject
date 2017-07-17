package DAL;

import controller.DBManager;
import model.Customer;
import model.Rental;

public class RentalQueries{
    private DBManager dbManager;

    public RentalQueries(){
        this.dbManager = DBManager.getInstance();
    }

    public void storeRental(Customer customer, Rental rental){

    }
}

