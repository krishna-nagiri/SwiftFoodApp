package com.infy.services;
import java.util.Scanner;
import com.infy.models.*;
import com.infy.data.*;
import com.infy.ui.*;
public class UserLoginService {

    private String userName;
    private String password;
    private String emailId;
    private long contactNumber;
    boolean dispRun = true;
    Scanner sc = new Scanner(System.in);
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setEmailId(String emailId){
        this.emailId =emailId;
    }
    public String getEmailId(){
        return this.emailId;
    }
    public void setContactNumber(long contactNumber){
        this.contactNumber = contactNumber;
    }
    public long getContactNumber(){
        return this.contactNumber;
    }
    public void displayMenu(){
        while(dispRun){
            System.out.println("========== SWIFTFOOD LOGIN ==========");
            System.out.println("1. Customer\n2. Restaurant  \n3. Delivery Partner \n4. Admin \n5.Exit");
            System.out.print("Select you role/Option : ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                System.out.println("Do you have an account?\n1.yes - Login  \n2.No - Signup");
                int action = sc.nextInt();
                sc.nextLine();
                    if(action == 1) customerLogin();
                    else registerCustomer(sc);
                    break;
                case 2 :
                    System.out.println("Do you have an account?\n1.yes - Login  \n2.No - Signup");
                    int action1 = sc.nextInt();
                    sc.nextLine();
                    if(action1 == 1)restaurantLogin();
                    else registerRestaurant(sc);
                    break;
                case 3: 
                    System.out.println("Do you have an account?\n1.yes - Login  \n2.No - Signup");
                    int action2 = sc.nextInt();
                    sc.nextLine();
                    if(action2 == 1)deliveryPartnerLogin();
                    else registerDeliveryPartner(sc);
                    break;
                case 4:
                    adminLogin();
                    break;
                case 5:
                    System.out.println("Logging Out!......");
                    dispRun = false;
                    break;
                default:
                    break;
            }
        }
    }
    public void adminLogin(){       // admin login block
        System.out.println("Enter Admin Username : ");
        setUserName(sc.nextLine());
        System.out.println("Enter password : ");
        setPassword(sc.nextLine());

        if(getUserName().equals("admin") && getPassword().equals("admin123@")){ //hard coded credentials.
            System.out.println("Login Successfull! \nYou will be redirected to admin homepage.\n");
            AdminHomePage.showMenu();
        }else{
            System.out.println("Hey Stranger! We don't recognize that login. Spell check your info and try again!");
        }
    }
    public void customerLogin(){       // customer login block
        System.out.println("Enter Customer Email : ");
        setEmailId(sc.nextLine());
        System.out.println("Enter password : ");
        setPassword(sc.nextLine());

        boolean found = false;

        for(Customer c : CustomerData.getAllCustomers()){
            if(c.getEmailId().equals(getEmailId()) && c.getPassword().equals(getPassword())){
                System.out.println("Welcome! "+c.getCustomerName()+" Login Successfull!");
                found = true;
                break;
                // TODO : Redirect to Customer Dashboard
            }
        }
        if(!found){
            System.out.println("Hey Stranger! We don't recognize that login. Spell check your info and try again!");
        }
    }
    // Reister Customer
    public static void registerCustomer(Scanner sc){
            System.out.println("\n--- Register New Customer ---");
            try{
                System.out.println("Enter Name of the Customer : ");
                String custName = sc.nextLine();
                if(custName.isEmpty()) throw new IllegalArgumentException("Name of Customer Cannot be Empty");

                System.out.println("Enter Phone Number");
                long custPhone;
                try {
                    custPhone = Long.parseLong(sc.nextLine());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid contact number. Must be digits only.");
                }
                System.out.print("Enter email ID: ");
                String custEmail = sc.nextLine();
                if (custEmail.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");

                System.out.println("Enter Password");
                String custPassword = sc.nextLine();
                if(custPassword.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

                System.out.print("Enter restaurant address: ");
                String custAddress = sc.nextLine();
                if (custAddress.isEmpty()) throw new IllegalArgumentException("Address cannot be empty");

                Customer newCustomer = new Customer(custName, custPhone, custAddress, custEmail, custPassword);
                CustomerData.addCustomer(newCustomer);
                System.out.println("Customer Registered Successfully");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
    }
    public void restaurantLogin(){      // restaurant login block
        System.out.println("Enter Restaurant Email : ");
        setEmailId(sc.nextLine());
        System.out.println("Enter password : ");
        setPassword(sc.nextLine());

        boolean found = false;
        
        for(Restaurant r : RestaurantData.getAllRestaurants()){
            if(r.getEmailId().equals(getEmailId()) && r.getPassword().equals(getPassword())){
                System.out.println("Welcome! "+r.getRestaurantName()+" Login Successfull!");
                found = true;
                break;
                //  TODO : Redirect to restaurant dashboard
            }
        }
        if(!found){
            System.out.println("Hey Stranger! We don't recognize that login. Spell check your info and try again!");
        }
    }
    public static void registerRestaurant(Scanner sc){
            System.out.println("\n--- Register New Restaurant ---");
            try {
                System.out.print("Enter restaurant name: ");
                String name = sc.nextLine();
                if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

                System.out.print("Enter email ID: ");
                String email = sc.nextLine();
                if (email.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");

                System.out.println("Enter Password");
                String password = sc.nextLine();
                if(password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

                System.out.print("Enter contact number: ");
                long contact;
                try {
                    contact = Long.parseLong(sc.nextLine());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid contact number. Must be digits only.");
                }

                System.out.print("Enter restaurant address: ");
                String address = sc.nextLine();
                if (address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty");

                float rating = 0.0f;
                Restaurant newRestaurant = new Restaurant(name, email, password, contact, address, rating);
                RestaurantData.addRestaurant(newRestaurant);
                System.out.println("Restaurant registered successfully!");

                }
                catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                }
    }
    public void deliveryPartnerLogin(){     // delivery partner login block
        System.out.println("Enter Delivery Partner Email : ");
        setEmailId(sc.nextLine());
        System.out.println("Enter password : ");
        setPassword(sc.nextLine());

        boolean found = false;

        for(DeliveryPartner d : DeliveryPartnerData.getAllDeliveryPartners()){
            if(d.getEmailId().equals(getEmailId()) && d.getPassword().equals(getPassword())){
                System.out.println("Welcome! "+d.getPatnerName()+" Login Successfull! ");
                found = true;
                break;
                //TODO : Redirect to Delivery Partner Dashboard
            }
        }
        if(!found){
            System.out.println("Hey Stranger! We don't recognize that login. Spell check your info and try again!");
        }
    }
    public static void registerDeliveryPartner(Scanner sc){
            System.out.println("\n--- Register New Restaurant ---");
            try{
                System.out.println("Enter name of the Driver: ");
                String name = sc.nextLine();
                if(name.isEmpty()) throw new IllegalArgumentException("Name of the Driver cannot be empty");

                System.out.println("Enter Email Id :");
                String emailId = sc.nextLine();
                if(emailId.isEmpty()) throw new IllegalArgumentException("EmailId cannot be empty");

                System.out.println("Enter password : ");
                String password = sc.nextLine();
                if(password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");

                System.out.print("Enter contact number: ");
                long contact;
                try {
                    contact = Long.parseLong(sc.nextLine());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid contact number. Must be digits only.");
                }

                DeliveryPartner newPartner = new DeliveryPartner(name, emailId, password, contact);
                DeliveryPartnerData.addDeliveryPartner(newPartner);
                System.out.println("Delivery Partner registered successfully!");

            } 
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } 
            catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
    }
}
