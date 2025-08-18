package main.com.infy.ui.customer;

import main.com.infy.models.Customer;
import java.util.Scanner;

public class CustomerHomePage {

    public static void launch(Customer customer, Scanner sc) {
        boolean running = true;

        while (running) {
            System.out.println("\n========== WELCOME, " + customer.getCustomerName().toUpperCase() + " ==========");
            System.out.println("1. Place an Order");
            System.out.println("2. View My Orders");
            System.out.println("3. Track Active Orders");
            System.out.println("4. Rate Delivered/Failed Orders");
            System.out.println("5. Search for Food/Restaurants");
            System.out.println("6. Manage My Profile");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    CustomerOrderMenu.placeOrder(customer, sc);
                    break;
                case 2:
                    CustomerOrderMenu.viewMyOrders(customer);
                    break;
                case 3:
                    CustomerOrderMenu.trackActiveOrders(customer, sc);
                    break;
                case 4:
                    CustomerOrderMenu.rateDeliveredOrFailedOrders(customer, sc);
                    break;
                case 5:
                    CustomerSearchMenu.launchSearchMenu(sc);
                    break;
                case 6:
                    CustomerProfileMenu.manageProfile(customer, sc);
                    break;
                case 7:
                    running = false;
                    System.out.println("--> Logged out successfully!");
                    break;
                default:
                    System.out.println("X-> Invalid choice. Try again!");
            }
        }
    }
}
