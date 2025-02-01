package com.carrental.service;

import com.carrental.database.DatabaseConnection;
import com.carrental.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRentalService {

    // Method to get all cars from the database
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cars.add(new Car(resultSet.getInt("id"), resultSet.getString("model"), resultSet.getBoolean("is_available")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    // Method to rent a car
    public void rentCar(int carId, int customerId) {
        // SQL Queries to check car availability, check customer existence, update car, and insert rental
        String checkCarQuery = "SELECT is_available FROM cars WHERE id = ?";
        String checkCustomerQuery = "SELECT id FROM customers WHERE id = ?";
        String updateCarQuery = "UPDATE cars SET is_available = false WHERE id = ?";
        String insertRentalQuery = "INSERT INTO rentals (car_id, customer_id) VALUES (?, ?)";

        // Establishing a connection inside try-with-resources block
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {

            // Step 1: Check if the car exists and is available
            try (PreparedStatement checkCarStmt = connection.prepareStatement(checkCarQuery)) {
                checkCarStmt.setInt(1, carId);
                ResultSet carResultSet = checkCarStmt.executeQuery();

                if (carResultSet.next()) {
                    boolean isAvailable = carResultSet.getBoolean("is_available");

                    if (!isAvailable) {
                        System.out.println("Sorry, the car is already rented.");
                        return; // Stop the process if the car is not available
                    }

                    // Step 2: Check if the customer exists
                    try (PreparedStatement checkCustomerStmt = connection.prepareStatement(checkCustomerQuery)) {
                        checkCustomerStmt.setInt(1, customerId);
                        ResultSet customerResultSet = checkCustomerStmt.executeQuery();

                        if (customerResultSet.next()) {
                            // Step 3: Customer exists, proceed with renting the car

                            // Update the car's availability to false
                            try (PreparedStatement updateCarStmt = connection.prepareStatement(updateCarQuery)) {
                                updateCarStmt.setInt(1, carId);
                                updateCarStmt.executeUpdate();
                                System.out.println("Car ID " + carId + " is now marked as rented.");
                            }

                            // Step 4: Insert a new rental record into the rentals table
                            try (PreparedStatement insertRentalStmt = connection.prepareStatement(insertRentalQuery)) {
                                insertRentalStmt.setInt(1, carId);
                                insertRentalStmt.setInt(2, customerId);
                                insertRentalStmt.executeUpdate();
                                System.out.println("Car rented successfully to Customer ID " + customerId + "!");
                            }} else {
                            System.out.println("Customer ID " + customerId + " does not exist.");
                        }
                    }
                } else {
                    System.out.println("Car ID " + carId + " does not exist.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}