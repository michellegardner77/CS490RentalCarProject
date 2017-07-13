package models;

import java.time.Year;
import java.util.Objects;

public class Car {
    private String id;


    public Car() {
    }

    public Car(final String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public Car setId(final String id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Car car = (Car) o;
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Car{" + "id='" + id + '\'' + '}';
    }
}
