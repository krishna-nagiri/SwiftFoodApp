package com.infy.ui.restaurant;

import com.infy.models.Restaurant;
import com.infy.ui.restaurant.RestaurantFoodMenu;
import com.infy.ui.restaurant.RestaurantOrderMenu;
import com.infy.ui.restaurant.RestaurantEarningsMenu;
import com.infy.ui.restaurant.RestaurantProfileMenu;

import java.util.Scanner;

public class RestaurantHomePage {

    public static void restaurantDisplay(Restaurant restaurant) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========= Welcome, " + restaurant.getRestaurantName() + " =========");
            System.out.println("1. Add Food to Menu");
            System.out.println("2. View My Menu");
            System.out.println("3. Delete Food Item");
            System.out.println("4. Orders");
            System.out.println("5. View My Profile");
            System.out.println("6. Total Earnings");
            System.out.println("7. Total Orders");
            System.out.println("8. Export Earnings to CSV");
            System.out.println("0. Logout");
            System.out.print("Choose your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    RestaurantFoodMenu.addFoodToMenu(restaurant.getRestaurantId(), sc);
                    break;
                case 2:
                    RestaurantFoodMenu.viewMyMenu(restaurant.getRestaurantId());
                    break;
                case 3:
                    RestaurantFoodMenu.deleteFoodItem(restaurant.getRestaurantId(), sc);
                    break;
                case 4:
                    RestaurantOrderMenu.orderManagementMenu(restaurant, sc);
                    break;
                case 5:
                    RestaurantProfileMenu.viewMyProfile(restaurant, sc);
                    break;
                case 6:
                    RestaurantEarningsMenu.calculateTotalEarningsAndOrders(restaurant, "Earnings");
                    break;
                case 7:
                    RestaurantEarningsMenu.calculateTotalEarningsAndOrders(restaurant, "Orders");
                    break;
                case 8:
                    RestaurantEarningsMenu.exportEarningsToCSV(restaurant.getRestaurantId(), sc);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("X Invalid choice. Try again.");
                    break;
            }
        }
    }
}
