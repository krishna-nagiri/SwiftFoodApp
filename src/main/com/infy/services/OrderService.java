package main.com.infy.services;

import main.com.infy.models.Order;
import main.com.infy.models.Restaurant;
import main.com.infy.data.RestaurantData;

import java.time.LocalTime;

public class OrderService {


    public static boolean isRestaurantOpenNow(String restaurantId) {
        Restaurant restaurant = RestaurantData.getRestaurantById(restaurantId);
        if (restaurant == null) return false;

        LocalTime now = LocalTime.now();
        return !now.isBefore(restaurant.getStartTime()) && !now.isAfter(restaurant.getEndTime());
    }


    @Deprecated
    public static void evaluateOrderStatus(Order order) {
        Restaurant restaurant = RestaurantData.getRestaurantById(order.getRestaurantId());
        if (restaurant != null) {
            LocalTime orderTime = order.getOrderTime().toLocalTime();
            LocalTime start = restaurant.getStartTime();
            LocalTime end = restaurant.getEndTime();

            if (!orderTime.isBefore(start) && !orderTime.isAfter(end)) {
                order.setStatus("Accepted");
                System.out.println("-> Order has been accepted by the restaurant.");
            } else {
                order.setStatus("Rejected");
                System.out.println("-> Order rejected: Restaurant is closed at this time.");
            }
        } else {
            order.setStatus("Rejected");
            System.out.println("-> Order rejected: Restaurant not found.");
        }
    }
}
