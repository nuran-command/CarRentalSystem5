package com.carrental.model;

public interface Vehicle {
    int getId();
    String getModel();
    boolean isAvailable();
    void setAvailable(boolean available);
}