package com.infy.ui.restaurant;

import com.infy.models.Food;
import com.infy.models.Order;
import com.infy.data.FoodData;
import com.infy.data.OrderData;

import java.util.List;
import java.util.Scanner;

public class RestaurantFoodMenu {
    public static void addFoodToMenu(String restaurantId,Scanner sc){
        System.out.println("==========  Adding New Food to Menu ==========\n");
        System.out.println("Enter Name of Food : ");
        String foodName = sc.nextLine();

        System.out.println("Enter Cuisine Type (Eg : South Indian,Hyderabadi,...) : ");
        String cuisine = sc.nextLine();

        System.out.println("Enter Food Type (Veg/Non-Veg) : ");
        String type = sc.nextLine().toLowerCase();

        System.out.println("Enter Unit Price : ");
        double price = sc.nextDouble();
        sc.nextLine();

        Food newFood = new Food(foodName, cuisine, type, price, restaurantId);
        List<Food> allFoods = FoodData.getAllFoods();
        allFoods.add(newFood);
        FoodData.saveAllFoods();

        System.out.println("-> Food Added successfully with Id : "+newFood.getFoodId());
    }

    public static void viewMyMenu(String restaurantId){
        System.out.println("\n ==========  MENU  ========== \n");
        List<Food> allFoods = FoodData.getAllFoods();
        boolean found = false;

        for(Food food : allFoods){
            if(food.getRestaurantId().equalsIgnoreCase(restaurantId)){
                System.out.println("--------------------------------------------------------");
                System.out.println(food.getFoodId());
                System.out.println(food.toString());
                System.out.println("--------------------------------------------------------");
                found = true;
            }
        }
        if(!found){
            System.out.println("X-> No Food Items Avaliable in Menu!..");
        }
    }
    public static void deleteFoodItem(String restaurantId, Scanner sc){
        List<Food> allFoods = FoodData.getAllFoods();
        List<Order> allOrders = OrderData.getAllOrders();
        boolean found = false;

        System.out.println("\n ==========  MENU  ==========\n");
        for(Food food : allFoods){
            if(food.getRestaurantId().equalsIgnoreCase(restaurantId)){
                System.out.println("-> "+food.getFoodName() + "   -   Food Id : "+food.getFoodId());
                found = true;
            }
        }
        if(!found){
            System.out.println("No Food Found to Delete!..");
            return;
        }
        System.out.println("Enter Food Id of the Food Item you want to delete : ");
        String foodToDelete = sc.nextLine();

        boolean isLinkedToActiveOrder = false;

        for(Order order : allOrders){
            if(order.getRestaurantId().equalsIgnoreCase(restaurantId)){
                boolean linked = order.getOrderedFoods().stream().anyMatch(food -> food.getFoodId().equalsIgnoreCase(foodToDelete));
                if(linked && (order.getStatus().equalsIgnoreCase("Placed") || order.getStatus().equalsIgnoreCase("Accepted"))){
                    isLinkedToActiveOrder = true;
                }
            }
        }
        if(isLinkedToActiveOrder){
            System.out.println("X -> Cannot delete food item!. It is linked to an active Order. ");
            return;
        }
        boolean deleted = FoodData.deleteFoodById(foodToDelete, restaurantId);
        if(deleted){
            System.out.println("Food Item Deleted Successfully!..");
        }
        else{
            System.out.println("Invalid Food Id or it doesn't belong to you!...");
        }
    }
}
