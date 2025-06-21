package com.infy.models;

public class DeliveryPartner {
    private String partnerName;
    private String partnerId = "DRI";
    private static int counter;
    private String emailId;
    private String password;
    private long contactNumber;
    private static int partnerCount;

    public DeliveryPartner(String partnerName,String emailId,String password,long contactNumber){
        this.partnerName = partnerName;
        this.emailId = emailId;
        this.password = password;
        this.contactNumber = contactNumber;
        this.partnerId += counter;
        counter++;
        partnerCount++;
    }
    static{
        counter = 101;
        partnerCount = 0;
    }
    // setter methods
    public void setPatnerName(String patnerName){
        this.partnerName = patnerName;
    }
    public void setEmailId(String emailId){
        this.emailId = emailId;
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
    public static int getPartnerCount(){
        return partnerCount;
    }

    public String toString(){
        return "Name : " + partnerName +"\nID : "+partnerId+
        "\nContact(email/phone) : "+emailId+"\n\t\t"+contactNumber ;
    }
}
