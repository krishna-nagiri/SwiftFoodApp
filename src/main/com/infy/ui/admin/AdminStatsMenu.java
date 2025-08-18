package main.com.infy.ui.admin;

import java.util.Scanner;
import main.com.infy.models.Customer;
import main.com.infy.models.Restaurant;
import main.com.infy.models.DeliveryPartner;
import main.com.infy.data.CustomerData;
import main.com.infy.data.RestaurantData;
import main.com.infy.data.DeliveryPartnerData;


public class AdminStatsMenu {
    public static int getTotalCustomers(){
        return CustomerData.getCustomerCount();
    }

    public static int getTotalRestaurants(){
        return RestaurantData.getRestaurantCount();
    }

    public static int getTotalDeliveryPartners(){
        return DeliveryPartnerData.getDeliveryPartnerCount();
    }

    public static void showUserDetails(Scanner sc){
        boolean statRunning = true;

        while (statRunning) {
            System.out.println("\n--- User Details ---");
            System.out.println("1. List Customers");
            System.out.println("2. List Restaurants");
            System.out.println("3. List Delivery Partners");
            System.out.println("4. Go Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                        System.out.println(" ===============  LIST OF CUSTOMERS  ===============");
                        for(Customer c : CustomerData.getAllCustomers()){
                            System.out.println("Name : "+c.getCustomerName() +  "\nCustomer Id : "+c.getCustomerId());
                            System.out.println("Customer EmailId : "+c.getEmailId() + "\nAddress : "+c.getAddress());
                        }
                        break;
                case 2:
                        System.out.println(" ===============  LIST OF RESTAURANTS  ===============");
                        for(Restaurant r : RestaurantData.getAllRestaurants()){
                            System.out.println("Name : "+r.getRestaurantName()  +  "\nRating : "+r.getRating() +"\nAddress : "+r.getRestaurantAddress());
                        }
                        break;
                case 3:
                        System.out.println(" ===============  LIST OF PARTNERS  ===============");
                        for(DeliveryPartner dp : DeliveryPartnerData.getAllDeliveryPartners()){
                            System.out.println("Name : "+dp.getPatnerName() +"\nContact : "+dp.getContactNumber());
                        }
                        break;
                case 4:
                        statRunning = false;
                        break;
                default:
                        System.out.println("Invalid Option. Try Again!..");
                    break;
            }
        }
    }
}
