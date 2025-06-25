package com.infy.models;

public class DeliveryPartner {
    private String partnerName;
    private String partnerId;
    private String emailId;
    private String password;
    private long contactNumber;
    private double totalEarnings;

    public DeliveryPartner(String partnerId,String partnerName,String emailId,String password,long contactNumber){
        this.partnerName = partnerName;
        this.emailId = emailId;
        this.password = password;
        this.contactNumber = contactNumber;
        this.partnerId = partnerId;
        this.totalEarnings = 0.0;

    }

    // setter methods
    public void setPatnerName(String patnerName){
        this.partnerName = patnerName;
    }
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }
    public void setTotalEarnings(double totalEarnings){
        this.totalEarnings = totalEarnings;
    }

    public void setContactNumber(long contactNumber){
        this.contactNumber = contactNumber;
    }
    public void setPassword(String passoword){
        this.password = passoword;
    }
    

    // getter methods
    public String getPatnerName(){
        return this.partnerName;
    }
    public String getPatnerId(){
        return this.partnerId;
    }
    public String getEmailId(){
        return this.emailId;
    }
    public long getContactNumber(){
        return this.contactNumber;
    }
    public String getPassword(){
        return this.password;
    }
    public double getTotalEarnings(){
        return this.totalEarnings;
    }
    public void addEarnings(double amount){
        this.totalEarnings += amount;
    }
    public String toString(){
        return "Name : " + partnerName +"\nID : "+partnerId+
        "\nContact(email/phone) : "+emailId+"\n\t\t\t"+contactNumber +"\nTotal Earnings : "+(totalEarnings*10.0)/10.0;
    }
}
