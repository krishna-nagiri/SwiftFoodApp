package main.com.infy.models;

import java.time.LocalTime;


public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private long restaurantContact;
    private String emailId;
    private String password;
    private double rating;
    private LocalTime startTime;
    private LocalTime endTime;
    private double totalEarnings;
    
    public Restaurant(String restaurantId,String name, String emailId, String password, long restaurantContact,String restaurantAddress, double rating, LocalTime startTime, LocalTime endTime) {
        this.restaurantId = restaurantId;
        this.restaurantName = name;
        this.emailId = emailId;
        this.password = password;
        this.restaurantAddress = restaurantAddress;
        this.rating = rating;
        this.restaurantContact = restaurantContact;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    // Setter Methods
    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public void setStartTime(LocalTime starTime){
        this.startTime = starTime;
    }
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }
    public void setPassword(String passoword){
        this.password = passoword;
    }
    public void setrestaurantContact(long restaurantContact){
        this.restaurantContact = restaurantContact;
    }
    

    //Getter Methods
    public long getrestaurantContact(){
        return this.restaurantContact;
    }
    public String getRestaurantName(){
        return this.restaurantName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRestaurantAddress(){
        return this.restaurantAddress;
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }
    public LocalTime getEndTime(){
        return this.endTime;
    }
    public String getRestaurantId(){
        return this.restaurantId;
    }
    public String getEmailId(){
        return this.emailId;
    }
    public double getRating(){
        return this.rating;
    }
    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void addEarnings(double amount) {
        this.totalEarnings += amount;
    }

    public boolean isWithinOperatingHours() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(startTime) && !now.isAfter(endTime);
    }

    public String toString(){
        return "Restaurant Name : "+restaurantName+"\nId: "+restaurantId+
        "\nRating : " +rating+"\n"+"Address : "+restaurantAddress +"\n"+"Start Time : "+startTime+ " AM \n"+"Closing Time : "+endTime+" PM\n";
    }
    
}
