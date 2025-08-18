package main.com.infy;

import main.com.infy.data.RestaurantData;
//import com.infy.models.*;
import main.com.infy.services.*;
//import com.infy.data.*;
import main.com.infy.util.DeliverySimulationUtil;

public class App {
    public static void main(String[] args) throws Exception {
    	
        DeliverySimulationUtil.autoCompleteDeliveredOrders();
        
        RestaurantData.updateRestaurantRatings();
        
        UserLoginService u1 = new UserLoginService();
        
        u1.displayMenu();
        
    }
    
}
