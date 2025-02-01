package com.carrental.model;

public class Rental {
    private int rentalId;
    private int carId;
    private int customerId;

    public Rental(int rentalId, int carId, int customerId) {
        this.rentalId = rentalId;
        this.carId = carId;
        this.customerId = customerId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public int getCarId() {
        return carId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
