package main.com.infy.ui.deliveryPartner;

import main.com.infy.models.Order;
import main.com.infy.data.OrderData;

import java.util.List;

public class DeliveryPartnerEarningsMenu {

    public static void viewMyEarnings(String partnerId) {
        List<Order> allOrders = OrderData.getAllOrders();
        double totalEarnings = 0.0;
        int deliveredOrders = 0;

        for (Order order : allOrders) {
            if (order.getDeliveryPartnerId().equalsIgnoreCase(partnerId)
                    && order.getStatus().equalsIgnoreCase("Delivered")) {
                totalEarnings += order.getTotalPrice() * 0.10; // 10% commission
                deliveredOrders++;
            }
        }

        System.out.println("\n==========   MY EARNINGS  ==========\n");
        System.out.println("<-> Total Delivered Orders : " + deliveredOrders);
        System.out.printf("<-> Total Earnings (10%% per order): Rs %.2f\n", totalEarnings);
        System.out.println("===================================");
    }
}
