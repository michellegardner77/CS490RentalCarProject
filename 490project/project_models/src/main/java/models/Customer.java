package models;

import java.util.Objects;

public class Customer {
    private String name;
    private String phone;
    private String address;

    public Customer() {
    }

    public Customer(final String name, final String phone, final String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Customer setName(final String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(final String address) {
        this.address = address;
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
        final Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) && Objects.equals(getPhone(), customer.getPhone()) && Objects
                .equals(getAddress(), customer.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getAddress());
    }

    @Override
    public String toString() {
        return "Customer{" + "name='" + name + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + '}';
    }
}
