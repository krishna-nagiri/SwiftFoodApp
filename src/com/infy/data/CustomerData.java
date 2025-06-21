package com.infy.data;

import com.infy.models.Customer;
import com.infy.util.JsonFileUtil;
import com.infy.util.FilePaths;
import com.google.gson.reflect.TypeToken;

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
}
