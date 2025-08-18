package main.com.infy.ui.customer;

import main.com.infy.data.*;
import main.com.infy.models.*;
import main.com.infy.services.OrderService;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random; 

public class CustomerOrderMenu {

    // place a new order
    public static void placeOrder(Customer customer, Scanner sc) {
        List<Food> allFoods = new ArrayList<>(FoodData.getAllFoods());
        List<Restaurant> allRestaurants = new ArrayList<>(RestaurantData.getAllRestaurants());


        // 🔍 Map for quick restaurant lookUp
        Map<String, Restaurant> restaurantMap = new HashMap<>();
        for (Restaurant r : allRestaurants) {
            restaurantMap.put(r.getRestaurantId(), r);
        }

        // 🔤 Sort foods by Food ID
        allFoods.sort(Comparator.comparing(Food::getFoodId));

        // 🧾 Display all food items with restaurant info
        System.out.println("\n========== TODAYs DEALS ==========\n");
        for (Food food : allFoods) {
            Restaurant r = restaurantMap.get(food.getRestaurantId());
            if (r != null) {
                System.out.println("->  " + food.getFoodId() + " | " + food.getFoodName() + " (Rs. " + food.getUnitPrice() + ")");
                System.out.println("   From: " + r.getRestaurantName() + " | Rating: " + r.getRating());
                System.out.println("-----------------------------------------");
            }
        }

        // 🛒 Customer selects food items
        List<Food> cart = new ArrayList<>();
        String selectedRestaurantId = null;
        System.out.println("\nEnter Food IDs to add to your cart (type 'done' to finish):");

        while (true) {
            System.out.print("Enter Food ID: ");
            String input = sc.next();

            if (input.equalsIgnoreCase("done")) break;

            boolean found = false;
            for (Food food : allFoods) {
                if (food.getFoodId().equalsIgnoreCase(input)) {
                    if (selectedRestaurantId == null) {
                        selectedRestaurantId = food.getRestaurantId();
                    } else if (!food.getRestaurantId().equals(selectedRestaurantId)) {
                        System.out.println("X.  You can only order from one restaurant per order.");
                        found = true;
                        break;
                    }
                    cart.add(food);
                    System.out.println("-> " + food.getFoodName() + " added to cart.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("X  Invalid Food ID. Try again.");
            }
        }

        if (cart.isEmpty()) {
            System.out.println("X  No food selected. Order cancelled.");
            return;
        }

        // ⏰ Check if restaurant is open
        if (!OrderService.isRestaurantOpenNow(selectedRestaurantId)) {
            System.out.println("X The selected restaurant is currently closed. Cannot place order.");
            return;
        }

        // 💰 Show bill
        double total = cart.stream().mapToDouble(Food::getUnitPrice).sum();
        System.out.println("(.) Total Bill: Rs. " + total);

        // 💳 Payment method
        String paymentMethod = "";
        while (true) {
            System.out.println("\nChoose Payment Method:\n1. Card\n2. UPI\n3. Cash on Delivery");
            System.out.print("Your choice: ");
            int option = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine();

            if (option == 1) {
                System.out.print("Enter Card Number: ");
                String card = sc.nextLine();
                if (card.length() >= 4) {
                    paymentMethod = "Card (****" + card.substring(card.length() - 4) + ")";
                    break;
                } else {
                    System.out.println("X  Invalid card number. Try again.");
                }
            } else if (option == 2) {
                System.out.print("Enter UPI ID: ");
                String upi = sc.nextLine();
                if (!upi.isEmpty()) {
                    paymentMethod = "UPI (" + upi + ")";
                    break;
                } else {
                    System.out.println("X  UPI ID cannot be empty.");
                }
            } else if (option == 3) {
                paymentMethod = "Cash on Delivery";
                break;
            } else {
                System.out.println("X  Invalid option. Try again.");
            }
        }

        //  Delivery address
        System.out.print("\n-> Use your profile address as delivery address? (yes/no): ");
        String useProfileAddress = sc.nextLine();
        String deliveryAddress = useProfileAddress.equalsIgnoreCase("yes")
            ? customer.getAddress()
            : getManualAddress(sc);

        //  Assign an available delivery partner
        List<DeliveryPartner> partners = DeliveryPartnerData.getAllDeliveryPartners();
        List<Order> allOrders = OrderData.getAllOrders();
        List<DeliveryPartner> availablePartners = new ArrayList<>();

        for (DeliveryPartner dp : partners) {
            boolean isBusy = false;
            for (Order order : allOrders) {
                if (order.getDeliveryPartnerId().equals(dp.getPatnerId())
                    && order.getStatus().equalsIgnoreCase("Accepted")
                    && order.getDeliveryTime() != null
                    && LocalDateTime.now().isBefore(order.getDeliveryTime())) {
                    isBusy = true;
                    break;
                }
            }
            if (!isBusy) availablePartners.add(dp);
        }

        if (availablePartners.isEmpty()) {
            System.out.println("X No delivery partners available right now.");
            return;
        }

        DeliveryPartner assignedDP = availablePartners.get(new Random().nextInt(availablePartners.size()));

        //  Create order object
        String orderId = OrderData.generateNextOrderId();
        Order order = new Order(
            orderId,
            customer.getCustomerId(),
            customer.getCustomerName(),
            selectedRestaurantId,
            assignedDP.getPatnerId(),
            cart,
            paymentMethod,
            deliveryAddress
        );
        order.setStatus("Pending");

        OrderData.addOrder(order);

        System.out.println("\n-> Order placed successfully! Waiting for delivery partner to accept.");
        System.out.println("-----------------------------------");
        System.out.println(order);
    }

    private static String getManualAddress(Scanner sc) {
        System.out.print("Enter delivery address: ");
        return sc.nextLine();
    }
    public static void viewMyOrders(Customer customer){
        List<Order> allOrders = OrderData.getAllOrders();
        List<Restaurant> allRestaurants = RestaurantData.getAllRestaurants();

        Map<String, Restaurant> restaurantMap = new HashMap<>();
        for(Restaurant r : allRestaurants){
            restaurantMap.put(r.getRestaurantId(), r);
        }
        boolean found = false;
        System.out.println("\n=================  YOUR ORDERS  =================\n");
        for(Order order : allOrders){
            if(order.getCustomerId().equals(customer.getCustomerId())){
                found = true;
                Restaurant rest = restaurantMap.get(order.getRestaurantId());
                String restName = (rest != null) ? rest.getRestaurantName() : "Unknown";

                System.out.println("-> Order Id : "+order.getOrderId());
                System.out.println("-> Restaurant : "+restName);
                System.out.println("-> Order Status : "+ order.getStatus());

                if(order.getStatus().equalsIgnoreCase("Rejected")){
                    System.out.println("-> Reason : "+order.getFailureReason());
                }
                System.out.println("-> Items : ");
                for(Food food : order.getOrderedFoods()){
                    System.out.println("\t   -  "+food.getFoodName() + "   - Rs "+food.getUnitPrice());
                }
                double total = order.getOrderedFoods().stream().mapToDouble(Food :: getUnitPrice).sum();
                System.out.println("-> Total : Rs."+total);
                System.out.println("-> Payment : "+order.getPaymentMethod());
                System.out.println("-> Delivery Address : "+ order.getDeliveryAddress());
                System.out.println("-> Order At : "+ order.getOrderTime());

                if(order.getStatus().equalsIgnoreCase("Delivered")){
                    System.out.println("-> Rating Given : "+(order.getCustomerRating() == 0 ? "Not Yet Rated": order.getCustomerRating()));
                    System.out.println("-> Delivered At : "+order.getDeliveryTime());
                }
                System.out.println("-------------------------------------------------------------------\n");
            }
        }
        if(!found){
            System.out.println("You haven't placed any orders yet!. ");
        }
    }
    public static void rateDeliveredOrFailedOrders(Customer customer,Scanner sc){
        List<Order> allOrders = OrderData.getAllOrders();

        boolean anyRated = false;

        System.out.println("  ========== Rate Orders ========== \n");
        for(Order order : allOrders){
            boolean isRelevant = order.getCustomerId().equals(customer.getCustomerId());
            boolean isCompleted = order.getStatus().equalsIgnoreCase("Delivered") || order.getStatus().equalsIgnoreCase("Rejected");
            boolean isUnrated = order.getCustomerRating() == -1;

            if(isRelevant && isCompleted && isUnrated){
                System.out.println("-> Order Id : "+order.getOrderId());
                System.out.println("-> Status : "+order.getStatus());
                if(order.getStatus().equalsIgnoreCase("Rejected")){
                    System.out.println("X-> Reason : "+order.getFailureReason());
                }
                System.out.println("-> Items : ");
                for(Food food : order.getOrderedFoods()){
                    System.out.println("\t    -  "+food.getFoodName());
                }
                int rating = 0;
                while(true){
                    System.out.println("-> Please Rate this order (1-5) : ");
                    String input = sc.nextLine();
                    try{
                        rating = Integer.parseInt(input);
                        if(rating > 0 && rating <=5) break;
                    }catch(NumberFormatException ignored){}
                    System.out.println("-> Invalid Input!. Enter a number from 1-5.");
                }
                order.setCustomerRating(rating);
                anyRated = true;
                System.out.println("<-> Thank you for rating!\n-----------------------------------\n");
            }
        }
        if(anyRated){
            OrderData.saveOrdersToFile();
            RestaurantData.updateRestaurantRatings();
        }
        else{
            System.out.println("X-> No Pending Orders found!..");
        }
    }
    public static void trackActiveOrders(Customer customer,Scanner sc){
        List<Order> allOrders = OrderData.getAllOrders();
        boolean activeOrderfound = false;

        System.out.println(" ==========  ACTIVE ORDERS  ==========");
        for(Order order : allOrders){
            if(!order.getCustomerId().equals(customer.getCustomerId()))continue;
            boolean isPendingOrAccepted = order.getStatus().equalsIgnoreCase("Pending") || order.getStatus().equalsIgnoreCase("Accepted");
            boolean isInDelivery = order.getDeliveryTime() !=null && LocalDateTime.now().isBefore(order.getDeliveryTime());

            if(isPendingOrAccepted && isInDelivery){
                activeOrderfound = true;
                System.out.println("-> Order Id : "+ order.getOrderId());
                System.out.println("-> Status : "+ order.getStatus());
                System.out.println("-> Delivery Partner ID : "+order.getDeliveryPartnerId());
                System.out.println("-> Placed At : "+order.getOrderTime());
                System.out.println("-> Expected Delivery Time : "+order.getDeliveryTime());

                long minutesLeft = java.time.Duration.between(LocalDateTime.now(),order.getDeliveryTime()).toMinutes();
                System.out.println("-> Order will be Delivered in : "+minutesLeft+" Minutes");

                System.out.println("-> Items :");
                for(Food food: order.getOrderedFoods()){
                   System.out.println("   - " + food.getFoodName() + " (₹" + food.getUnitPrice() + ")");
                }
                System.out.println("----------------------------------------\n");
            }
            if(!activeOrderfound){
                System.out.println("X -> You don't have any active Orders right now!..");
            }
        }
    }


}
