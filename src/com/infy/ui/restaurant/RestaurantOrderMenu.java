package com.infy.ui.restaurant;

import com.infy.models.Order;
import com.infy.models.Restaurant;
import com.infy.data.OrderData;
import com.infy.data.RestaurantData;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class RestaurantOrderMenu {
    public static void orderManagementMenu(Restaurant restaurant, Scanner sc){
        boolean runningInOrderMenu = true;

        while (runningInOrderMenu) {
            System.out.println("==========  ORDER MANAGEMENT  ==========");
            System.out.println("1. View Incoming Orders");
            System.out.println("2. View Accepted Orders");
            System.out.println("3. View Rejected Orders");
            System.out.println("4. View All Orders");
            System.out.println("5. View Order By Id");
            System.out.println("0. Go Back");

            System.out.println("Enter your Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
               case 1 : viewOrdersByStatus(restaurant.getRestaurantId(), "Placed");
                        break;
                case 2 : viewOrdersByStatus(restaurant.getRestaurantId(), "Accepted");
                        break;
                case 3 : viewOrdersByStatus(restaurant.getRestaurantId(), "Rejected");
                        break;
                case 4 : viewOrdersByStatus(restaurant.getRestaurantId(), "All");
                        break;
                case 5 : viewOrderById(restaurant.getRestaurantId(), sc);
                        break;
                case 0 : 
                        System.out.println("Ok! Returning to main menu...");
                        runningInOrderMenu = false;
                        break;
                
                default : 
                        System.out.println("Invalid Choice. Try again!..");
                        break;
            }
        }
    }
    public static void viewOrdersByStatus(String restrauntId, String statusFilter){
        List<Order> allOrders = OrderData.getAllOrders();
        boolean found = false;
        boolean updated = false;

        System.out.println("==========  Orders " + statusFilter.toUpperCase() + "  ==========");
        for(Order order : allOrders){
            if(order.getRestaurantId().equalsIgnoreCase(restrauntId)){
                if(order.getStatus().equalsIgnoreCase("Pending")){
                    Restaurant rest = RestaurantData.getRestaurantById(order.getRestaurantId());
                    if(rest != null){
                        LocalTime orderTime = order.getOrderTime().toLocalTime();
                        boolean invalidTime = orderTime.isBefore(rest.getStartTime()) || orderTime.isAfter(rest.getEndTime());
                        boolean invalidAddress = order.getDeliveryAddress() == null || order.getDeliveryAddress().isEmpty();
                        boolean invalidPayment = order.getPaymentMethod() == null  || order.getPaymentMethod().isEmpty();

                        if(invalidTime || invalidPayment || invalidAddress){
                            order.setStatus("Rejected");
                        }
                        else{
                            order.setStatus("Accepted");
                        }
                        updated = true;
                    }
                }
                if(statusFilter.equalsIgnoreCase("ALL") || order.getStatus().equalsIgnoreCase(statusFilter)){
                    System.out.println(order);
                    System.out.println("-----------------------------------");
                    found = true;
                }
            }
        }
        if(updated){
            OrderData.saveOrdersToFile();
        }
        if(!found){
            System.out.println("X-> No orders Found!..");
        }
    }
    public static void viewOrderById(String restaurantId, Scanner sc){
        System.out.println("Enter the Order Id : ");
        String orderId  = sc.nextLine();
        List<Order> allOrders = OrderData.getAllOrders();
        boolean found = false;

        for(Order order : allOrders){
            if(order.getRestaurantId().equalsIgnoreCase(restaurantId) && order.getOrderId().equalsIgnoreCase(orderId)){
                System.out.println("========== Order Details ==========");
                System.out.println(order);
                System.out.println("===================================");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("X-> No order found with Id : "+orderId);
        }
    }
}
