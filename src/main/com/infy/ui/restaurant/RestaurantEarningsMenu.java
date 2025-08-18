package main.com.infy.ui.restaurant;

import main.com.infy.models.Order;
import main.com.infy.models.Restaurant;
import main.com.infy.data.OrderData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class RestaurantEarningsMenu {

    public static void calculateTotalEarningsAndOrders(Restaurant restaurant, String whatToReturn) {
        double totalEarnings = 0.0;
        int totalOrders = 0;

        List<Order> orders = OrderData.getAllOrders();
        for (Order order : orders) {
            if (order.getRestaurantId().equals(restaurant.getRestaurantId())) {
                totalEarnings += order.getTotalPrice();
                totalOrders++;
            }
        }

        if (whatToReturn.equalsIgnoreCase("Earnings")) {
            System.out.println("-> Total Earnings : Rs." + totalEarnings + "\n");
        } else {
            System.out.println("-> Total Orders : " + totalOrders + "\n");
        }
    }

    public static void exportEarningsToCSV(String restaurantId, Scanner sc) {
        List<Order> allOrders = OrderData.getAllOrders();
        double totalEarnings = 0.0;

        File exportDir = new File("data/exports");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        String fileName = "data/exports/" + restaurantId + "_earnings.csv"; 

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Order Id,Customer Id,Amount Rs,Payment Method");

            for (Order order : allOrders) {
                if (order.getRestaurantId().equalsIgnoreCase(restaurantId)
                        && ((order.getStatus().equalsIgnoreCase("Accepted")) || order.getStatus().equalsIgnoreCase("Delivered"))) {

                    double amount = order.calculateTotalAmount();
                    amount -= (amount * 0.05);
                    totalEarnings += amount;

                    writer.println(order.getOrderId() + "," +
                                   order.getCustomerId() + "," +
                                   amount + "," +
                                   order.getPaymentMethod());
                }
            }

            writer.println();
            writer.println("Total Earnings," + totalEarnings);  

            System.out.println("-> Earnings exported successfully to: " + fileName);
        } catch (IOException e) {
            System.out.println("X-> Error writing to CSV: " + e.getMessage());
        }
    }
}
