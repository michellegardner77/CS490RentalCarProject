package model;

import java.time.Year;
import java.util.Objects;

public class CarSpec {
    private String carId;
    private String make;
    private String model;
    private Year year;
    private CarSizeEnum size;

    public CarSpec() {
    }

    public CarSpec(final String carId, final String make, final String model, final Year year, final CarSizeEnum size) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.size = size;
    }

    public String getCarId() {
        return carId;
    }

    public CarSpec setCarId(final String carId) {
        this.carId = carId;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarSpec setMake(final String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarSpec setModel(final String model) {
        this.model = model;
        return this;
    }

    public Year getYear() {
        return year;
    }

    public CarSpec setYear(final Year year) {
        this.year = year;
        return this;
    }

    public CarSizeEnum getSize() {
        return size;
    }

    public CarSpec setSize(final CarSizeEnum size) {
        this.size = size;
        return this;
    }

    @Override
    public String toString() {
        return "CarSpec{" + "carId='" + carId + '\'' + ", make='" + make + '\'' + ", model='" + model + '\'' + ", year=" + year + ", size="
                + size + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarSpec carSpec = (CarSpec) o;
        return Objects.equals(getCarId(), carSpec.getCarId()) && Objects.equals(getMake(), carSpec.getMake()) && Objects
                .equals(getModel(), carSpec.getModel()) && Objects.equals(getYear(), carSpec.getYear()) && getSize() == carSpec.getSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getMake(), getModel(), getYear(), getSize());
    }
}
