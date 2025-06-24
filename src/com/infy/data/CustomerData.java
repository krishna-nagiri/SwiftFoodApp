package com.infy.data;

import com.infy.models.Customer;
import com.infy.models.Order;
import com.infy.util.JsonFileUtil;
import com.infy.util.FilePaths;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class CustomerData {
    private static final Type CUSTOMER_LIST_TYPE = new TypeToken<List<Customer>>() {}.getType();
    private static final JsonFileUtil<Customer> jsonUtil = new JsonFileUtil<>(FilePaths.CUSTOMER_FILE, CUSTOMER_LIST_TYPE);

    private static List<Customer> customerList = jsonUtil.readData();

    public static List<Customer> getAllCustomers() {
        return customerList;
    }

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
        jsonUtil.writeData(customerList);
        saveCustomersToFile();
    }

    public static int getCustomerCount() {
        return customerList.size();
    }

    public static Customer findByEmail(String email) {
        for (Customer c : customerList) {
            if (c.getEmailId().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }
    public static void saveCustomersToFile() {
        try (Writer writer = new FileWriter("data/customers.json")) {
            new GsonBuilder().setPrettyPrinting().create().toJson(customerList, writer);
        } catch (IOException e) {
            System.out.println("Error saving customers to file: " + e.getMessage());
        }
    }
    public static String generateNextCustomerId() {
        List<Customer> allCustomers = getAllCustomers();
        int max = 0;
        for (Customer c : allCustomers) {
            try {
                int num = Integer.parseInt(c.getCustomerId().replace("CUS", ""));
                if (num > max) max = num;
            } catch (Exception e) {
                // Ignore malformed IDs
            }
        }
        return String.format("CUS%03d", max + 1);
    }
}

