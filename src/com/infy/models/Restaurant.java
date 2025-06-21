package com.infy.models;

public class Restaurant {
    private String restaurantId = "RES";
    private String restaurantName;
    private String restaurantAddress;
    private long restaurantContact;
    private String emailId;
    private String password;
    private float rating;
    private static int counter;
    private static int restaurantCount;
    
    public Restaurant(String name,String emailId,String password,long restaurantContact,String restaurantAddress,float rating){
        this.restaurantId += counter;
        this.restaurantName = name;
        this.emailId = emailId;
        this.password = password;
        this.restaurantAddress = restaurantAddress;
        this.rating = rating;
        this.restaurantContact = restaurantContact;
        counter++;
        ++restaurantCount;
        //System.out.println("Restaurant  "+this.restaurantName+" added successfully.\nRestaurant Count = "+restaurantCount);
        
    }
    static{
        counter = 1000;
        restaurantCount = 0;
    }
    // public Restaurant(){
    //     System.out.println("non_parameterized customer.");
    // }
    public String getEmailId(){
        return this.emailId;
    }
    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String passoword){
        this.password = passoword;
    }
    public void setrestaurantContact(long restaurantContact){
        this.restaurantContact = restaurantContact;
    }
    public long getrestaurantContact(){
        return this.restaurantContact;
    }
    public String getRestaurantName(){
        return this.restaurantName;
    }
    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }
    public static int getRestaurantCount(){
        return restaurantCount;
    }
    public String getRestaurantAddress(){
        return this.restaurantAddress;
    }
    public String getRestaurantId(){
        return this.restaurantId;
    }
    public String toString(){
        return "Restaurant Name : "+restaurantName+"\nId: "+restaurantId+
        "Rating : " +rating+"\n"+"Address : "+restaurantAddress +"\n";
    }
    
}
