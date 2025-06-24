package com.infy.ui.admin;

import com.infy.data.OrderData;
import com.infy.models.Order;

import java.util.List;

public class AdminOrderMenu {
    public static void viewAllOrders(){
        System.out.println("\n==========  ALL ORDERS  ==========\n");
       
        List<Order> allOrders = OrderData.getAllOrders();
        
        if(allOrders.isEmpty()){
            System.out.println("✖ No Orders Found.");
            return;
        }

        for(Order o : allOrders){
            System.out.println(o);
            System.out.println("-------------------------------------------");
        }

    }
}
