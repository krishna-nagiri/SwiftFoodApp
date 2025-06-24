package com.infy.ui.admin;

import java.util.Scanner;

public class AdminHomePage {

    public static void showMenu() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        while (running) {
            System.out.println("\n=== Welcome, Admin ===");
            System.out.println("1. Total Customers");
            System.out.println("2. Total Restaurants");
            System.out.println("3. Total Delivery Partners");
            System.out.println("4. User Details");
            System.out.println("5. Delete Restaurant");
            System.out.println("6. Delete Delivery Partner");
            System.out.println("7. View All Orders");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Total Customers: " + AdminStatsMenu.getTotalCustomers());
                    break;
                case 2:
                    System.out.println("Total Restaurants: " + AdminStatsMenu.getTotalRestaurants());
                    break;
                case 3:
                    System.out.println("Total Delivery Partners: " + AdminStatsMenu.getTotalDeliveryPartners());
                    break;
                case 4:
                    AdminStatsMenu.showUserDetails(sc);
                    break;
                case 5:
                    AdminDeleteMenu.deleteRestaurant(sc);
                    break;
                case 6:
                    AdminDeleteMenu.deleteDeliveryPartner(sc);
                    break;
                case 7:
                    AdminOrderMenu.viewAllOrders();
                    break;
                case 0:
                    System.out.println("Logging Out!...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }
}
