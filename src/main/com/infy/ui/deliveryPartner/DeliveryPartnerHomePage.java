package main.com.infy.ui.deliveryPartner;

import main.com.infy.models.DeliveryPartner;

import java.util.Scanner;

public class DeliveryPartnerHomePage {

    public static void displayMenu(DeliveryPartner dp) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========   Welcome, " + dp.getPatnerName() + "!  ==========\n");

        while (running) {
            System.out.println("\n========== DELIVERY PARTNER MENU ==========");
            System.out.println("1. View My Profile");
            System.out.println("2. Orders");
            System.out.println("3. My Earnings");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DeliveryPartnerProfileMenu.viewMyProfile(dp, sc);
                    break;
                case 2:
                    DeliveryPartnerOrderMenu.orderManagement(dp, sc);
                    break;
                case 3:
                    DeliveryPartnerEarningsMenu.viewMyEarnings(dp.getPatnerId());
                    break;
                case 4:
                    System.out.println("Logging out... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
