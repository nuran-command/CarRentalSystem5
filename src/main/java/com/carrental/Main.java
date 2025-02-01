package com.carrental;

import com.carrental.model.Car;
import com.carrental.service.CarRentalService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarRentalService service = new CarRentalService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Displaying options
            System.out.println("1. View All Cars\n2. Rent a Car\n3. Exit");
            int choice = scanner.nextInt();

            // Handling user choices
            switch (choice) {
                case 1:
                    // Viewing all cars
                    List<Car> cars = service.getAllCars();
                    for (Car car : cars) {
                        System.out.println("ID: " + car.getId() + ", Model: " + car.getModel() + ", Available: " + car.isAvailable());
                    }
                    break;

                case 2:
                    // Renting a car
                    System.out.println("Enter Car ID to rent:");
                    int carId = scanner.nextInt();
                    System.out.println("Enter Customer ID:");
                    int customerId = scanner.nextInt();
                    service.rentCar(carId, customerId);  // Rent car logic
                    System.out.println("Car rented successfully!");
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}