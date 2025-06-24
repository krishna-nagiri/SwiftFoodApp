package com.infy.data;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.infy.models.Food;
import com.infy.util.FilePaths;
import com.infy.util.JsonFileUtil;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class FoodData {
    private static final Type FOOD_LIST_TYPE = new TypeToken<List<Food>>() {}.getType();
    private static final JsonFileUtil<Food> jsonUtil = new JsonFileUtil<>(FilePaths.FOOD_FILE, FOOD_LIST_TYPE);
    private static final String FILE_PATH = "data/foods.json";

    private static List<Food> foodList = jsonUtil.readData();

    public static List<Food> getAllFoods() {
        return foodList;
    }

    public static void addFood(Food food) {
        foodList.add(food);     // takes a food object
        jsonUtil.writeData(foodList);
    }

    public static int getTotalFoodsInList() {
        return foodList.size();
    }

    public static void saveAllFoods() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(foodList, writer);
        } catch (Exception e) {
            System.out.println("Error saving food data: " + e.getMessage());
        }
    }

    public static boolean deleteFoodById(String foodId, String restaurantId) {
        boolean removed = foodList.removeIf(food ->
            food.getFoodId().equalsIgnoreCase(foodId) &&
            food.getRestaurantId().equalsIgnoreCase(restaurantId)
        );

        if (removed) {
            saveAllFoods();
        }

        return removed;
    }
    public static boolean updateFoodPrice(String foodId,String restaurantId,double price){
        boolean updated = false;
        for(Food food : foodList){
            if(food.getFoodId().equalsIgnoreCase(foodId) && food.getRestaurantId().equalsIgnoreCase(restaurantId)){
                food.setUnitPrice(price);
                saveAllFoods();
                updated = true;
            }
        }
        return updated;
    }
    public static boolean updateFoodName(String foodId,String restaurantId,String foodName){
        boolean updated = false;
        for(Food food: foodList){
            if(food.getFoodId().equalsIgnoreCase(foodId) && food.getRestaurantId().equalsIgnoreCase(restaurantId)){
                food.setFoodName(foodName);
                saveAllFoods();
                updated = true;
            }
        }
        return updated;
    }
    public static boolean updateFoodCuisine(String foodId,String restaurantId,String cuisine){
        boolean updated = false;
        for(Food food: foodList){
            if(food.getFoodId().equalsIgnoreCase(foodId) && food.getRestaurantId().equalsIgnoreCase(restaurantId)){
                food.setCuisine(cuisine);
                saveAllFoods();
                updated = true;
            }
        }
        return updated;
    } 
    public static List<Food> getMenuForRestaurant(String restaurantId) {
        return foodList.stream()
                    .filter(f -> f.getRestaurantId().equals(restaurantId))
                    .collect(Collectors.toList());
    }
    

}
