package model;

import java.util.Date;

/**
 * Created by mgard on 7/28/2017.
 */
public class CarSpecRentalJoin {
    private String carId;
    private String make;
    private String model;
    private String year;
    private CarSizeEnum size;
    private Date returnDate;
    private Date rentDate;

    public CarSpecRentalJoin() {
    }

    public CarSpecRentalJoin(final String carId, final String make, final String model,Date rentDate, Date returnDate) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
