package main.com.infy.services;

import java.util.Scanner;

public class UserLoginService {

    private final Scanner sc = new Scanner(System.in);
    private final LoginService loginService = new LoginService();
    private final SignupService signupService = new SignupService();

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== SWIFTFOOD LOGIN ==========");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant");
            System.out.println("3. Delivery Partner");
            System.out.println("4. Admin");
            System.out.println("0. Exit");
            System.out.print("Select your role/option: ");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    handleCustomer();
                    break;
                case "2":
                    handleRestaurant();
                    break;
                case "3":
                    handleDeliveryPartner();
                    break;
                case "4":
                    loginService.adminLogin(sc);
                    break;
                case "0":
                    System.out.println("Logging out... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void handleCustomer() {
        System.out.println("\nDo you have an account?");
        System.out.println("1. Yes - Login");
        System.out.println("2. No - Sign Up");
        System.out.print("Your choice: ");
        String input = sc.nextLine();
        if (input.equals("1")) {
            loginService.customerLogin(sc);
        } else if (input.equals("2")) {
            signupService.registerCustomer(sc);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void handleRestaurant() {
        System.out.println("\nDo you have an account?");
        System.out.println("1. Yes - Login");
        System.out.println("2. No - Sign Up");
        System.out.print("Your choice: ");
        String input = sc.nextLine();
        if (input.equals("1")) {
            loginService.restaurantLogin(sc);
        } else if (input.equals("2")) {
            signupService.registerRestaurant(sc);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void handleDeliveryPartner() {
        System.out.println("\nDo you have an account?");
        System.out.println("1. Yes - Login");
        System.out.println("2. No - Sign Up");
        System.out.print("Your choice: ");
        String input = sc.nextLine();
        if (input.equals("1")) {
            loginService.deliveryPartnerLogin(sc);
        } else if (input.equals("2")) {
            signupService.registerDeliveryPartner(sc);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }
}
