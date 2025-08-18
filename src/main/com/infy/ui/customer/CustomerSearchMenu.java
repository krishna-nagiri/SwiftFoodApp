package main.com.infy.ui.customer;

import main.com.infy.models.Food;
import main.com.infy.models.Restaurant;
import main.com.infy.data.FoodData;
import main.com.infy.data.RestaurantData;

import java.util.List;
import java.util.Scanner;

public class CustomerSearchMenu {
    public static void launchSearchMenu(Scanner sc){
        boolean running = true;

        while(running){
            System.out.println("\n=========== FOOD SEARCH MENU ===========");
            System.out.println("1. Search by Food Name");
            System.out.println("2. Search by Restaurant Name");
            System.out.println("3. Filter Restaurants by Rating");
            System.out.println("4. Go Back");
            System.out.print("Choose an option: ");

            int option = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine();
            switch (option) {
                case 1:
                    searchByFoodName(sc);
                    break;
                case 2:
                    searchByRestaurantName(sc);
                    break;
                case 3:
                    filterByRating(sc);
                    break;
                case 4:
                    running = false;
                    break;
                default:

                    System.out.println("X-> Invalid option! Try again.");
            }
        }
    }
    public static void searchByFoodName(Scanner sc){
        List<Food> allFoods = FoodData.getAllFoods();
        List<Restaurant> allRestaurants = RestaurantData.getAllRestaurants();

        System.out.println("-> Enter food Name : ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for(Food food : allFoods){
            if(food.getFoodName().toLowerCase().contains(keyword)){
                Restaurant r = getRestaurantById(allRestaurants,food.getRestaurantId());
                if(r != null){
                    printFoodDetails(food,r);
                    found = true;
                }
            }
        }
        if(!found){
            System.out.println("X -> No food found matching your search!..");
        }
    }
    public static void searchByRestaurantName(Scanner sc){
        List<Restaurant> allRestaurants = RestaurantData.getAllRestaurants();
        List<Food> allFoods = FoodData.getAllFoods();

        System.out.println("Enter Restaurant Name to Search : ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for(Restaurant r: allRestaurants){
            if(r.getRestaurantName().toLowerCase().contains(keyword)){
                System.out.println("\n-> "+r.getRestaurantName()+ " | Rating: " + r.getRating());
                for(Food f : allFoods){
                    if(f.getRestaurantId().equals(r.getRestaurantId())){
                        printFoodDetails(f,r);
                        found = true;
                    }
                }
            }
        }
        if(!found){
            System.out.println("X -> No restaurant found with that Name.");
        }
    }
    public static void filterByRating(Scanner sc){
        List<Restaurant> allRestaurants = RestaurantData.getAllRestaurants();
        List<Food> allFoods = FoodData.getAllFoods();

        System.out.println("Enter Minimum Rating (1.0 - 5.0) : ");
        double rating = sc.hasNextDouble() ? sc.nextDouble() :-1;
        sc.nextLine();

        if(rating <1.0 || rating > 5.0){
            System.out.println("Invalid rating input!..");
            return;
        }
        boolean found = false;
        for(Restaurant r : allRestaurants){
            if(r.getRating() >= rating){
                System.out.println("-> "+r.getRestaurantName()+ " | Rating: " + r.getRating());
                for(Food food : allFoods){
                    if(food.getRestaurantId().equals(r.getRestaurantId())){
                        printFoodDetails(food,r);
                        found = true;
                    }
                }
            }
        }
        if(!found){
            System.out.println("X-> No Restaurants found with that rating or higher!..");
        }   
    }
    private static Restaurant getRestaurantById(List<Restaurant> restaurants,String id){
        for(Restaurant r: restaurants){
            if(r.getRestaurantId().equals(id)) return r;
        }
        return null;
    }
    public static void printFoodDetails(Food food, Restaurant r){
        System.out.println("-> " + food.getFoodId() + " | " + food.getFoodName() + " (Rs " + food.getUnitPrice() + ")");
        System.out.println("   From: " + r.getRestaurantName() + " | Rating: " + r.getRating());
        System.out.println("--------------------------------------------");
    }


}
