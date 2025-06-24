package com.infy.ui.admin;

import java.util.Scanner;
import com.infy.data.RestaurantData;
import com.infy.data.DeliveryPartnerData;

public class AdminDeleteMenu {
    public static void deleteOptions(Scanner sc){
        boolean running = true;

        while (running) {
            System.out.println("\n--- Delete User Menu ---");
            System.out.println("1. Delete Restaurant");
            System.out.println("2. Delete Delivery Partner");
            System.out.println("3. Go Back");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                        deleteRestaurant(sc);
                        break;
                case 2:
                        deleteDeliveryPartner(sc);
                        break;
                case 3:
                        running = false;
                        break;
                default:
                        System.out.println("X Invalid input. Try again!..");
                    break;
            }
        }
    }
    public static void deleteRestaurant(Scanner sc){
        System.out.println("Enter Restaurant Id  to delete : ");
        String delId = sc.nextLine();

        if(RestaurantData.deleteRestaurantById(delId)){
            System.out.println("✔ Restaurant Deleted Successfully!..");
        }else{
            System.out.println("✖ Failed to delete the Restaurant. Check ID.");
        }
    }
    public static void deleteDeliveryPartner(Scanner sc){
        System.out.println("Enter Delivery Partner Id  to Delete : ");
        String patId = sc.nextLine();

        if(DeliveryPartnerData.deleteDeliveryPartnerById(patId)){
            System.out.println("✔ Delivery Partner deleted successfully.");
        }else{
            System.out.println("✖ Failed to delete the Delivery Partner. Check ID.");
        }
    }
}
