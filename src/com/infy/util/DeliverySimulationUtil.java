package com.infy.util;

import com.infy.models.DeliveryPartner;
import com.infy.models.Order;
import com.infy.models.Restaurant;
import com.infy.data.DeliveryPartnerData;
import com.infy.data.OrderData;
import com.infy.data.RestaurantData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DeliverySimulationUtil {

    public static void autoCompleteDeliveredOrders() {
        List<Order> orders = OrderData.getAllOrders();
        List<DeliveryPartner> allPartners = DeliveryPartnerData.getAllDeliveryPartners();
        List<Restaurant> allRestaurants = RestaurantData.getAllRestaurants();
        boolean modified = false;

        for (Order order : orders) {
            if (order.getStatus().equalsIgnoreCase("Accepted")) {
                long minutesPassed = ChronoUnit.MINUTES.between(order.getDeliveryTime(), LocalDateTime.now());

                if (minutesPassed >= 5) {  
                    order.setStatus("Delivered");
                    order.setDeliveryTime(order.getDeliveryTime().plusMinutes(5));

                    double total = order.getTotalPrice();
                    double dpEarning = total * 0.10;
                    double restaurantEarning = total - dpEarning;

                    // Update DP earnings
                    for (DeliveryPartner dp : allPartners) {
                        if (dp.getPatnerId().equals(order.getDeliveryPartnerId())) {
                            dp.addEarnings(dpEarning);
                            break;
                        }
                    }

                    // Update restaurant earnings
                    for (Restaurant r : allRestaurants) {
                        if (r.getRestaurantId().equals(order.getRestaurantId())) {
                            r.addEarnings(restaurantEarning);
                            break;
                        }
                    }

                    modified = true;
                }
            }
        }

        if (modified) {
            OrderData.saveOrdersToFile();
            DeliveryPartnerData.saveAllDeliveryPartnersToFile();
            RestaurantData.saveRestaurantsToFile();
        }
    }
}
