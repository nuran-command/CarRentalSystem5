package com.carrental.model;

public class Car implements Vehicle {
    private int id;
    private String model;
    private boolean isAvailable;

    public Car(int id, String model, boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.isAvailable = isAvailable;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}