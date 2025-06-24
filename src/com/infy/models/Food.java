package com.infy.models;

import java.util.List;

public class Food {
    private String foodName;
    private String cuisine;
    private String foodType;
    // private int quantityAvailable;
    private double unitPrice;
    private String restaurantId;
    private  String foodId;

    // Used for adding new food
    public Food(String foodName, String cuisine, String foodType, double unitPrice, String restaurantId) {
        this.foodId  = generateNextFoodId();
        this.foodName = foodName;
        this.cuisine = cuisine;
        this.foodType = foodType;
        this.unitPrice = unitPrice;
        this.restaurantId = restaurantId;
    }

    // Used by GSON (no foodId generation)
    public Food(String foodId, String foodName, String cuisine, String foodType, double unitPrice, String restaurantId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.cuisine = cuisine;
        this.foodType = foodType;
        this.unitPrice = unitPrice;
        this.restaurantId = restaurantId;
    }

    private String generateNextFoodId() {
        List<Food> allFoods = com.infy.data.FoodData.getAllFoods();
        int max = 0;

        for (Food f : allFoods) {
            try {
                int num = Integer.parseInt(f.getFoodId().substring(3)); 
                if (num > max) max = num;
            } catch (Exception e) {
                // skip malformed ids
            }
        }

        return String.format("ITE%03d", max + 1);
    }


    //Setter Methods
    
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public void setCuisine(String cuisine){
        this.cuisine = cuisine;
    }
    public void setFoodType(String foodType){
        this.foodType = foodType;
    }
    // public void setQuantityAvailable(int quantityAvailable){
    //     this.quantityAvailable = quantityAvailable;
    // }
    public void setRestaurantId(String restaurantId){
        this.restaurantId = restaurantId;
    }

    // Getter Methods
    public String getFoodId(){
        return foodId;
    }
    // public int getQuantityAvailable(){
    //     return this.quantityAvailable;
    // }
    public String getRestaurantId(){
        return this.restaurantId;
    }
    public String getFoodType(){
        return this.foodType;
    }
    public String getCuisine(){
        return this.cuisine;
    }
    public double getUnitPrice(){
        return this.unitPrice;
    }
    public String getFoodName(){
        return this.foodName;
    }
    public String toString() {
        return "\nFood ID: " + foodId +
            "\nFood Name: " + foodName +
            "\nCuisine: " + cuisine +
            "\nFood Type: " + foodType +
            "\nPrice: ₹" + unitPrice + "\n";
    }


}
