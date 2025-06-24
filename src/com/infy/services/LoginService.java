package com.infy.services;

import java.io.Console;
import java.util.Scanner;

import com.infy.data.*;
import com.infy.models.*;
import com.infy.ui.admin.*;
import com.infy.ui.deliveryPartner.*;
import com.infy.ui.restaurant.*;
import com.infy.ui.customer.*;

public class LoginService {
    Console console = System.console();

    private String readPassword(Scanner sc) {
        if (console != null) {
            char[] passwordChars = console.readPassword("Enter password: ");
            return new String(passwordChars);
        } else {
            System.out.print("Enter password: ");
            return sc.nextLine();
        }
    }

    public void customerLogin(Scanner sc) {
        System.out.print("Enter Customer Email: ");
        String emailId = sc.nextLine();
        String password = readPassword(sc);

        for (Customer c : CustomerData.getAllCustomers()) {
            if (c.getEmailId().equals(emailId) && c.getPassword().equals(password)) {
                System.out.println("-> Welcome! " + c.getCustomerName() + " Login Successful!");
                CustomerHomePage.launch(c,sc);
                return;
            }
        }
        System.out.println("-> Invalid login credentials. Please try again.");
    }

    public void adminLogin(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String userName = sc.nextLine();
        String password = readPassword(sc);

        if (userName.equalsIgnoreCase("admin") && password.equals("admin123@")) {
            System.out.println("-> Welcome Admin!....");
            AdminHomePage.showMenu();
        } else {
            System.out.println("-> Invalid Admin credentials.");
        }
    }

    public void restaurantLogin(Scanner sc) {
        System.out.print("Enter Restaurant Email ID: ");
        String emailId = sc.nextLine();
        String password = readPassword(sc);

        for (Restaurant r : RestaurantData.getAllRestaurants()) {
            if (r.getEmailId().equals(emailId) && r.getPassword().equals(password)) {
                System.out.println("-> Welcome " + r.getRestaurantName() + ", Login Successful.");
                RestaurantHomePage.restaurantDisplay(r);
                return;
            }
        }
        System.out.println("-> Invalid Login Credentials. Try again!");
    }

    public void deliveryPartnerLogin(Scanner sc) {
        System.out.print("Enter Delivery Partner Email ID: ");
        String emailId = sc.nextLine();
        String password = readPassword(sc);

        for (DeliveryPartner dp : DeliveryPartnerData.getAllDeliveryPartners()) {
            if (dp.getEmailId().equals(emailId) && dp.getPassword().equals(password)) {
                System.out.println("-> Welcome " + dp.getPatnerName() + "!");
                DeliveryPartnerHomePage.displayMenu(dp);
                return;
            }
        }
        System.out.println("-> Invalid credentials. Try again!");
    }
}
