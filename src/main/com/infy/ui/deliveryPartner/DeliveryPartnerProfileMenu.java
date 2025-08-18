package main.com.infy.ui.deliveryPartner;

import main.com.infy.models.DeliveryPartner;
import main.com.infy.data.DeliveryPartnerData;

import java.util.Scanner;

public class DeliveryPartnerProfileMenu {

    public static void viewMyProfile(DeliveryPartner dp, Scanner sc) {
        boolean profileMenu = true;
        boolean updated = false;

        System.out.println("\n========== MY PROFILE ==========");
        System.out.println(dp.toString());

        while (profileMenu) {
            System.out.println("\n1. Edit My Profile");
            System.out.println("2. Go Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    updated = editMyProfile(dp, sc);
                    if (updated) {
                        DeliveryPartnerData.saveAllDeliveryPartnersToFile();
                        System.out.println("-> All changes saved successfully!");
                    } else {
                        System.out.println("X -> No changes made.");
                    }
                    break;
                case 2:
                    System.out.println("↩ Returning to main menu...");
                    profileMenu = false;
                    break;
                default:
                    System.out.println("Invalid Option. Try Again...");
                    break;
            }
        }
    }

    private static boolean editMyProfile(DeliveryPartner dp, Scanner sc) {
        boolean editing = true;
        boolean updated = false;

        while (editing) {
            System.out.println("\n==========  EDIT PROFILE  ==========\n");
            System.out.println("1. Name");
            System.out.println("2. Contact Number");
            System.out.println("3. Email Id");
            System.out.println("4. Password");
            System.out.println("5. Save and Exit");

            System.out.print("Choose field to edit: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter new Name: ");
                    dp.setPatnerName(sc.nextLine());
                    updated = true;
                    break;
                case 2:
                    System.out.print("Enter new Contact Number: ");
                    dp.setContactNumber(sc.nextLong());
                    sc.nextLine();
                    updated = true;
                    break;
                case 3:
                    System.out.print("Enter new Email Id: ");
                    dp.setEmailId(sc.nextLine());
                    updated = true;
                    break;
                case 4:
                    System.out.print("Enter new Password: ");
                    dp.setPassword(sc.nextLine());
                    updated = true;
                    break;
                case 5:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        return updated;
    }
}
