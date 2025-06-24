package com.infy.services;

import java.io.Console;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Scanner;

import com.infy.data.*;
import com.infy.models.*;

public class SignupService {
    Console console = System.console();

    private String readPassword(Scanner sc) {
        if (console != null) {
            char[] passwordChars = console.readPassword("Enter password: ");
            return new String(passwordChars);
        } else {
            System.out.print("Enter password: ");
            return sc.nextLine().trim();
        }
    }

    public void registerCustomer(Scanner sc) {
        System.out.println("===============  REGISTER A NEW CUSTOMER  ===============");
        try {
            String customerId = CustomerData.generateNextCustomerId();

            System.out.print("Enter Customer Name: ");
            String customerName = sc.nextLine().trim();
            if (customerName.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

            System.out.print("Enter Customer Email Id: ");
            String emailId = sc.nextLine().trim();
            if (emailId.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");

            System.out.print("Enter Contact Number: ");
            long contactNumber;
            try {
                contactNumber = Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid contact number. Must be digits only.");
            }

            System.out.print("Enter Customer Address: ");
            String address = sc.nextLine().trim();
            if (address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty");

            String password = readPassword(sc);
            if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

            Customer customer = new Customer(customerId, customerName, contactNumber, address, emailId, password);
            CustomerData.addCustomer(customer);
            System.out.println("-> Customer registered successfully! Login to continue.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    public void registerRestaurant(Scanner sc) {
        System.out.println("=============== REGISTER NEW RESTAURANT  ===============");
        try {
            String restaurantId = RestaurantData.generateNextRestaurantId();

            System.out.print("Enter the Name of the Restaurant: ");
            String restaurantName = sc.nextLine().trim();
            if (restaurantName.isEmpty()) throw new IllegalArgumentException("Restaurant Name cannot be empty!");

            System.out.print("Enter Email ID: ");
            String emailId = sc.nextLine().trim();
            if (emailId.isEmpty()) throw new IllegalArgumentException("Email ID cannot be empty!");

            System.out.print("Enter Contact Number: ");
            long contactNumber;
            try {
                contactNumber = Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Contact number must contain digits only.");
            }

            System.out.print("Enter Start Time (HH:mm): ");
            LocalTime startTime;
            try {
                startTime = LocalTime.parse(sc.nextLine().trim());
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Invalid Start Time: " + e.getMessage());
            }

            System.out.print("Enter End Time (HH:mm): ");
            LocalTime endTime;
            try {
                endTime = LocalTime.parse(sc.nextLine().trim());
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Invalid End Time: " + e.getMessage());
            }

            if (!endTime.isAfter(startTime)) {
                throw new IllegalArgumentException("End time must be after start time.");
            }

            System.out.print("Enter Restaurant Address: ");
            String address = sc.nextLine().trim();
            if (address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty!");

            String password = readPassword(sc);
            if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

            double rating = 0.0;
            Restaurant restaurant = new Restaurant(
                restaurantId, restaurantName, emailId, password,
                contactNumber, address, rating, startTime, endTime
            );

            RestaurantData.addRestaurant(restaurant);
            System.out.println("-> Restaurant registered successfully! Login to continue.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    public void registerDeliveryPartner(Scanner sc) {
        System.out.println("===============  REGISTER A NEW DELIVERY PARTNER  ===============");
        try {
            String dpId = DeliveryPartnerData.generateNextDeliveryPartnerId();

            System.out.print("Enter Delivery Partner Name: ");
            String dpName = sc.nextLine().trim();
            if (dpName.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

            System.out.print("Enter Email ID: ");
            String emailId = sc.nextLine().trim();
            if (emailId.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");

            System.out.print("Enter Contact Number: ");
            long contactNumber;
            try {
                contactNumber = Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid contact number. Must be digits only.");
            }

            String password = readPassword(sc);
            if (password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

            DeliveryPartner deliveryPartner = new DeliveryPartner(dpId, dpName, emailId, password, contactNumber);
            DeliveryPartnerData.addDeliveryPartner(deliveryPartner);
            System.out.println("-> Delivery Partner registered successfully! Login to continue.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}
