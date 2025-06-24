package com.infy.ui.customer;

import com.infy.models.Customer;
import com.infy.data.CustomerData;

import java.util.Scanner;

public class CustomerProfileMenu {
    public static void manageProfile(Customer customer,Scanner sc){
        boolean running = true;
        while(running){
            System.out.println("===============  CUSTOMER PROFILE  ================\n");
            System.out.println(customer);
            System.out.println("------------------------------------------------------\n");

            System.out.println("\n1. Edit my Profile\n");
            System.out.println("\n2. Go Back!.  \n");

            System.out.println("Enter your Choice : ");
            int choice = sc.hasNextLine() ? sc.nextInt() : -1;
            sc.nextLine();

            switch (choice) {
                case 1:
                        editProfile(customer,sc);
                    break;
                case 2:
                        running = false;
                        break;
                default:
                        System.out.println("Invalid option!.. ");
                    break;
            }
        }
    }
    private static void editProfile(Customer customer,Scanner sc){
        boolean editing = true;
        boolean updated = false;

        while (editing) {
            System.out.println("\n------ Edit Profile ------");
            System.out.println("1. Name");
            System.out.println("2. Contact Number");
            System.out.println("3. Email ID");
            System.out.println("4. Address");
            System.out.println("5. Password");
            System.out.println("6. Save and Exit");

            System.out.println("-> Choose a field to edit : ");
            int option = sc.hasNextLine() ? sc.nextInt() : -1;
            sc.nextLine();

            switch (option) {
                case 1:
                        System.out.println("Enter new name : ");
                        customer.setCustomerName(sc.nextLine());
                        updated = true;
                    break;
                case 2:
                        System.out.println("Enter new contact: ");
                        if(sc.hasNextLong()){
                            customer.setContactNumber(sc.nextLong());
                            sc.nextLine();
                            updated = true;
                        }else{
                            System.out.println("Invalid contact Number!..");
                            sc.nextLine();
                        }
                        break;
                case 3:
                        System.out.print("Enter new email ID: ");
                        customer.setEmailId(sc.nextLine());
                        updated = true;
                        break;
                case 4:
                        System.out.print("Enter new address: ");
                        customer.setAddress(sc.nextLine());
                        updated = true;
                        break;
                case 5:
                        System.out.print("Enter new password: ");
                        customer.setPassword(sc.nextLine());
                        updated = true;
                        break;
                case 6:
                        editing = false;
                        if(updated){
                            CustomerData.saveCustomersToFile();
                            System.out.println("->  Profile updated successfully!");
                        }else{
                            System.out.println("No Changes Made!.");
                        }
                        break;
                default:
                        System.out.println("Invalid Option. Try again!>.");
                        break;
            }
        }
    }
}
