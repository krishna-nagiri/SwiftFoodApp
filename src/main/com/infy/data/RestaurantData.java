package main.com.infy.data;

import main.com.infy.models.Order;
import main.com.infy.models.Restaurant;
import main.com.infy.util.JsonFileUtil;
import main.com.infy.util.FilePaths;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

    public static void saveRestaurantsToFile() {
        try (Writer writer = new FileWriter(FilePaths.RESTAURANT_FILE)) {
            new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(java.time.LocalTime.class, new LocalTimeAdapter())
                .create()
                .toJson(restaurantList, writer);
        } catch (IOException e) {
            System.out.println("Error saving restaurants to file: " + e.getMessage());
        }
}

    
    public static Restaurant getRestaurantById(String id) {
        List <Restaurant> restaurants = getAllRestaurants();
        for (Restaurant r : restaurants) {
            if (r.getRestaurantId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }
    public static String generateNextRestaurantId() {
        List<Restaurant> allRestaruants = getAllRestaurants();
        int max = 0;
        for (Restaurant r : allRestaruants) {
            try {
                int num = Integer.parseInt(r.getRestaurantId().replace("RES", ""));
                if (num > max) max = num;
            } catch (Exception e) {
                // Ignore malformed IDs
            }
        }
        return String.format("RES%03d", max + 1);
    }

    public static void updateRestaurantRatings() {
        List<Order> orders = OrderData.getAllOrders();
        List<Restaurant> restaurants = getAllRestaurants();

        for (Restaurant restaurant : restaurants) {
            int totalRating = 0;
            int ratedOrderCount = 0;

            for (Order order : orders) {
                if (order.getRestaurantId().equalsIgnoreCase(restaurant.getRestaurantId()) && order.getCustomerRating() > 0) {
                    totalRating += order.getCustomerRating();
                    ratedOrderCount++;
                }
            }

            if (ratedOrderCount > 0) {
                double averageRating = (double) totalRating / ratedOrderCount;
                restaurant.setRating(Math.round(averageRating * 10.0) / 10.0); // round to 1 decimal place
            }
        }

        saveRestaurantsToFile();
    }



}
