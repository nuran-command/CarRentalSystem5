package com.carrental.factory;

import com.carrental.model.Car;
import com.carrental.model.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, int id, String model, boolean isAvailable) {
        if ("Car".equalsIgnoreCase(type)) {
            return new Car(id, model, isAvailable);
        }
        throw new IllegalArgumentException("Invalid vehicle type");
    }
}