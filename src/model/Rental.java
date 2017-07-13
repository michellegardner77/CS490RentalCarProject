package model;

import java.util.Calendar;
import java.util.Objects;

public class Rental {
    private Calendar rentDate;
    private Calendar returnDate;
    private RentalStatus status;

    public Rental() {
    }

    public Rental(final Calendar rentDate, final Calendar returnDate, final RentalStatus status) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Calendar getRentDate() {
        return rentDate;
    }

    public Rental setRentDate(final Calendar rentDate) {
        this.rentDate = rentDate;
        return this;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public Rental setReturnDate(final Calendar returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public Rental setStatus(final RentalStatus status) {
        this.status = status;
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
        final Rental rental = (Rental) o;
        return Objects.equals(getRentDate(), rental.getRentDate()) && Objects.equals(getReturnDate(), rental.getReturnDate())
                && getStatus() == rental.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRentDate(), getReturnDate(), getStatus());
    }

    @Override
    public String toString() {
        return "Rental{" + "rentDate=" + rentDate + ", returnDate=" + returnDate + ", status=" + status + '}';
    }
}
