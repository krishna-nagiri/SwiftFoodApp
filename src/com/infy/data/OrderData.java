package com.infy.data;

import com.infy.models.Order;
import com.infy.util.FilePaths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderData {
    private static final String FILE_PATH = FilePaths.ORDER_FILE;
    private static List<Order> orders = new ArrayList<>();

    static{
        loadOrdersFromFile();
    }
    public static List<Order> getAllOrders(){
        return orders;
    }
    public static void addOrder(Order order){
        orders.add(order);
        saveOrdersToFile();
    }

    public static void saveOrdersToFile(){
        try(FileWriter fileWriter = new FileWriter(FILE_PATH)){
            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(java.time.LocalDateTime.class, new LocalDateTimeAdapter()).create();
            gson.toJson(orders,fileWriter);
        }catch(IOException e){
            System.out.println("Error Saving Orders  "+e.getMessage());
        }
    }
    
    public static void loadOrdersFromFile(){
        try(FileReader fileReader = new FileReader(FILE_PATH)){
            Gson gson = new GsonBuilder().registerTypeAdapter(java.time.LocalDateTime.class, new LocalDateTimeAdapter()).create();
            Type orderLisType = new TypeToken<ArrayList<Order>>() {}.getType();
            orders = gson.fromJson(fileReader,orderLisType);
            if(orders == null) orders = new ArrayList<>();
        }catch(IOException e){
            orders = new ArrayList<>();
        }
    }
    public static String generateNextOrderId() {
        List<Order> allOrders = getAllOrders();
        int max = 0;
        for (Order o : allOrders) {
            try {
                int num = Integer.parseInt(o.getOrderId().replace("ORD", ""));
                if (num > max) max = num;
            } catch (Exception e) {
                // Ignore malformed IDs
            }
        }
        return String.format("ORD%03d", max + 1);
    }

}
