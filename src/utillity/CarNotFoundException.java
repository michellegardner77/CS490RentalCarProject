package utillity;

/**
 * Created by mgard on 7/24/2017.
 */
public class CarNotFoundException extends Throwable {
    public String errorMessage;
    public String carId;

    public CarNotFoundException(final String errorMessage, final String carId) {
        this.errorMessage = errorMessage;
        this.carId = carId;
    }
}

