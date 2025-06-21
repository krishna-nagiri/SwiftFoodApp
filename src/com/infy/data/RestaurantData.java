package com.infy.data;


import com.infy.models.Restaurant;
import com.infy.util.JsonFileUtil;
import com.infy.util.FilePaths;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class RestaurantData {
    private static final Type RESTAURANT_LIST_TYPE = new TypeToken<List<Restaurant>>() {}.getType();
    private static final JsonFileUtil<Restaurant> jsonUtil = new JsonFileUtil<>(FilePaths.RESTAURANT_FILE, RESTAURANT_LIST_TYPE);


    private static List<Restaurant> restaurantList = jsonUtil.readData();
    
    public static List<Restaurant> getAllRestaurants(){
        return restaurantList;
    }

    public static void addRestaurant(Restaurant restaurant) {
        restaurantList.add(restaurant);
        jsonUtil.writeData(restaurantList);
    }

    public static int getRestaurantCount(){
        return restaurantList.size();
    }

    public static Restaurant findByEmail(String email) {
        for (Restaurant r : restaurantList) {
            if (r.getEmailId().equalsIgnoreCase(email)) {
                return r;
            }
        }
        return null;
    }
    
    public static boolean deleteRestaurantById(String restaurantId){
        List <Restaurant> restaurants = getAllRestaurants();
        boolean removed = restaurants.removeIf(r -> r.getRestaurantId().equals(restaurantId));
        if(removed){
            jsonUtil.writeData(restaurants);
        }
        return removed;
    }

}
