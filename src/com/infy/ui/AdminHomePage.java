package com.infy.ui;

import java.util.Scanner;

import com.infy.models.Customer;
import com.infy.data.*;
import com.infy.models.DeliveryPartner;
//import com.infy.models.Order;
import com.infy.models.Restaurant;
import com.infy.services.UserLoginService;

public class AdminHomePage {
    

        public static void showMenu() {
            boolean running = true;
            while (running) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n=== Welcome, Admin ===");
                System.out.println("1. Total Customers");
                System.out.println("2. Total Restaurants ");
                System.out.println("3. Total Delivery Partners");
                System.out.println("4. User Details");
                System.out.println("5. Delete Restaurant");
                System.out.println("6. Delete Delivery Partner");
                // System.out.println("4. View All Orders");    TODO
                System.out.println("0. Logout");

                int choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1:  
                            System.out.println("Total Customers: " + CustomerData.getCustomerCount());
                            break;
                    case 2 :
                            System.out.println("Total Restaurants: " + RestaurantData.getRestaurantCount());
                            break;
                    case 3 :
                            System.out.println("Total Delivery Partners: " + DeliveryPartnerData.getDeliveryPartnerCount());
                            break;
                    case 4 :
                            System.out.println("");
                            showUserDetails(sc);
                            break;
                    case 5 :
                            System.out.println("Enter Restaurant Id : ");
                            String resId = sc.nextLine();
                            sc.nextLine();
                            if(RestaurantData.deleteRestaurantById(resId)){
                                System.out.println("Restaurant Deleted successfully");
                            }else{
                                System.out.println("Failed to Delete the Restaurant.");
                            }
                            break;
                    case 6  :
                            System.out.println("Enter Delivery Partner Id: ");
                            String dpId = sc.nextLine();
                            sc.nextLine();
                            if(DeliveryPartnerData.deleteDeliveryPartnerById(dpId)){
                                System.out.println("Delivery Partner Deleted Successfully");
                            }else{
                                System.out.println("Failed to Delete Delivery Partner.");
                            }
                    case 0 :
                            System.out.println("Logging Out!.....");
                            running = false;
                            break;
                    default: System.out.println("Invalid input!");
                }
            }
            
        }
    public static void showUserDetails(Scanner sc){
        boolean statrunning = true;
        while(statrunning){
            System.out.println("\n" +"--- Today's Stats ---");
            System.out.println("\n1.List Customers \n2.List Restaurants \n3.List Delivery Patners\n4.Go Back!");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                    case 1:
                            System.out.println("========List of Customers========");
                            for(Customer c : CustomerData.getAllCustomers()){
                                System.out.println("Name of the Customer : "+c.getCustomerName()+"\nEmail Id : "+c.getEmailId()+"\n");
                            }
                            break;
                    case 2 :
                            System.out.println("========List of Restaurants========");
                            for(Restaurant r : RestaurantData.getAllRestaurants()){
                                System.out.println("Name of the Restaurant : "+r.getRestaurantName()+"\nAddress : "+r.getRestaurantAddress()+"\n");
                            }
                            break;
                    case 3 :
                            System.out.println("========List of Delivery Partners========");
                            for(DeliveryPartner dp : DeliveryPartnerData.getAllDeliveryPartners()){
                                System.out.println("Name of the Partner : "+dp.getPatnerName()+"\nContact Number : "+dp.getContactNumber()+"\n");
                            }
                            break;
                    case 4 :
                            System.out.println("OK!");
                            statrunning = false;
                            break;
                    default:
                        break;
                }
                System.out.println();
            }
            //System.out.println("Total Orders Placed: " + Order.getOrderCount());
        }
        // public static void viewOrders(){
// TODO
        // }
}
