package main.com.infy.ui.deliveryPartner;

import main.com.infy.models.DeliveryPartner;
import main.com.infy.models.Order;
import main.com.infy.models.Restaurant;
import main.com.infy.data.OrderData;
import main.com.infy.data.RestaurantData;
import main.com.infy.data.DeliveryPartnerData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class DeliveryPartnerOrderMenu {
    public static void orderManagement(DeliveryPartner dp,Scanner sc){
        boolean runningOrderManagement = true;
        while (runningOrderManagement) {
            System.out.println("\n==========  DELIVERY MANAGEMENT  ==========");
            System.out.println("1. Accept Order");
            System.out.println("2. Mark Order As Delivered");
            System.out.println("3. View Delivered Orders");
            System.out.println("0. Go Back");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    acceptOrder(dp.getPatnerId(), sc);
                    break;
                case 2:
                    markOrderAsDelivered(dp.getPatnerId(), dp, sc);
                    break;
                case 3:
                    viewOrdersByStatus(dp.getPatnerId(), "Delivered");
                    break;
                case 0:
                    runningOrderManagement = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public static void acceptOrder(String partnerId, Scanner sc) {
    List<Order> orders = OrderData.getAllOrders();
    boolean found = false;
    boolean updated = false;

    for (Order order : orders) {
        if (order.getDeliveryPartnerId().equalsIgnoreCase(partnerId)
                && order.getStatus().equalsIgnoreCase("Pending")) {

            // Calculate minutes since order placement
            long minutesPassed = ChronoUnit.MINUTES.between(order.getOrderTime(), LocalDateTime.now());

            if (minutesPassed >= 3 && order.getStatus().equalsIgnoreCase("Pending")) {
                order.setStatus("Rejected");
                order.setFailureReason("Delivery partner unable to reach your current destination.");
                order.setDeliveryTime(null);
                updated = true;
                continue; // Skip this and check next
            }

            // Show valid pending order to accept
            System.out.println("============== PENDING ORDER ==============");
            System.out.println(order);
            System.out.println("-------------------------------------------");

            System.out.println("Do you want to Accept this order : " + order.getOrderId() + " (Yes/No) : ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("YES")) {
                order.setStatus("Accepted");
                order.setDeliveryTime(LocalDateTime.now().plusMinutes(10)); // Simulated delivery time
                System.out.println("-> Order Accepted!..");
                found = true;
                updated = true;
                break;
            }
        }
    }

    if (!found) {
        System.out.println("No Pending Orders Assigned to you currently!..");
    }

    if (updated) {
        OrderData.saveOrdersToFile();
    }
}

    public static void markOrderAsDelivered(String partnerId, DeliveryPartner dp, Scanner sc){
        List<Order> allOrders = OrderData.getAllOrders();
        List<Restaurant> restaurants = RestaurantData.getAllRestaurants();
        boolean found = false;

        System.out.println("\n==========  MARK ORDER AS DELIVERED  ==========");
        System.out.print("Enter the Order Id to mark as Delivered: ");
        String orderId = sc.nextLine();

        for(Order order : allOrders){
            if(order.getDeliveryPartnerId().equalsIgnoreCase(partnerId) && order.getOrderId().equalsIgnoreCase(orderId) && order.getStatus().equalsIgnoreCase("Accepted")){
                LocalDateTime deliveryTime = order.getDeliveryTime().plusMinutes(5);
                order.setStatus("Delivered");
                order.setDeliveryTime(deliveryTime);

                double total = order.getTotalPrice();
                double dpEarnings = total * 0.10;
                double restaurantEarnings = total -dpEarnings;

                dp.addEarnings(dpEarnings);
                DeliveryPartnerData.saveAllDeliveryPartnersToFile(); //saving Delivery Partner earnings to delivery partner.
                for(Restaurant r : restaurants){
                    if(r.getRestaurantId().equals(order.getRestaurantId())){
                        r.addEarnings(restaurantEarnings);
                        RestaurantData.saveRestaurantsToFile();// saving Restaurant earnings to restaurant 
                        break;
                    }
                }
                System.out.println("-> Order  =  "+order.getOrderId()+" Delivered Successfully!..");
                OrderData.saveOrdersToFile();
                RestaurantData.saveRestaurantsToFile();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No matching 'Accepted' order found with Order Id: " + orderId);
        }
    }
    public static void viewOrdersByStatus(String partnerId, String statusFilter) {
        List<Order> allOrders = OrderData.getAllOrders();
        boolean found = false;

        System.out.println("\n==========  " + statusFilter.toUpperCase() + " ORDERS  ==========");

        for (Order order : allOrders) {
            if (order.getDeliveryPartnerId().equalsIgnoreCase(partnerId)
                    && order.getStatus().equalsIgnoreCase(statusFilter)) {
                System.out.println(order);
                System.out.println("-----------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("-> No " + statusFilter + " Orders Found!..");
        }
    }
}
