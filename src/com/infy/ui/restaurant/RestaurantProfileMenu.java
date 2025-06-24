package com.infy.ui.restaurant;

import com.infy.models.Restaurant;
import com.infy.data.RestaurantData;

import java.util.Scanner;

public class RestaurantProfileMenu {

    public static void viewMyProfile(Restaurant restaurant, Scanner sc) {
        boolean inProfileMenu = true;
        boolean updated = false;

        while (inProfileMenu) {
            System.out.println("\n========== MY PROFILE ==========\n");
            System.out.println(restaurant.toString());
            System.out.println("___________________________________");
            System.out.println("1. Edit My Profile");
            System.out.println("2. Go Back");

            System.out.print("Choose your option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : 
                    updated = editMyProfile(restaurant, sc);
                    if (updated) {
                        RestaurantData.saveRestaurantsToFile();
                        System.out.println("-> All changes saved successfully!");
                    } else {
                        System.out.println("X No changes made.");
                    }
                    break;
                case 2 : 
                    System.out.println("↩ Returning to main menu...");
                    inProfileMenu = false;
                    break;

                default :
                    System.out.println("X Invalid option. Try again.");
                    break;
            }
        }
    }

    private static boolean editMyProfile(Restaurant restaurant, Scanner sc) {
        boolean editing = true;
        boolean updated = false;

        while (editing) {
            System.out.println("\n==== EDIT PROFILE ====");
            System.out.println("1. Restaurant Name");
            System.out.println("2. Contact Number");
            System.out.println("3. Email Id");
            System.out.println("4. Address");
            System.out.println("5. Password");
            System.out.println("6. Save & Exit ↩ ");

            System.out.print("Choose field to edit: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 : 
                    System.out.print("Enter new Restaurant Name: ");
                    String newName = sc.nextLine();
                    restaurant.setRestaurantName(newName);
                    updated = true;
                    break;
                case 2 :
                    System.out.print("Enter new Contact Number: ");
                    long contact = sc.nextLong();
                    sc.nextLine();
                    restaurant.setrestaurantContact(contact);
                    updated = true;
                    break;
                case 3 :
                    System.out.print("Enter new Email Id: ");
                    String email = sc.nextLine();
                    restaurant.setEmailId(email);
                    updated = true;
                    break;
                case 4 :
                    System.out.print("Enter new Address: ");
                    String address = sc.nextLine();
                    restaurant.setRestaurantAddress(address);
                    updated = true;
                    break;
                case 5 :
                    System.out.print("Enter new Password: ");
                    String pwd = sc.nextLine();
                    restaurant.setPassword(pwd);
                    updated = true;
                    break;
                case 6 :
                    System.out.println("Exiting Profile Editor...");
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        return updated;
    }
}
