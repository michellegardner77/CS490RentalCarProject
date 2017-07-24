package model;

public class CarNotFoundException extends RuntimeException {
    public String errorMessage;
    public String carId;

    public CarNotFoundException() {
    }

    public CarNotFoundException(final String errorMessage, final String carId) {
        this.errorMessage = errorMessage;
        this.carId = carId;
    }
}
