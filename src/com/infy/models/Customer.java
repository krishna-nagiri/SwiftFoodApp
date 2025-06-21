package com.infy.models;

public class Customer {
	private  String customerId = "CUS";
	private String customerName;
	private long contactNumber;
	private String address;
    private static int counter;
    private String emailId;
    private String password;
    private static int customerCount;

    // First time user.
    public Customer(String customerName,long contactNumber,String address,String emailId,String password){
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.password = password;
        this.address = address;
        customerId += counter;
        counter++;
        customerCount++;
        
    }
   
    static{
        counter = 1001;
        customerCount = 0;
    }
    public static int getCustomerCount(){
        return customerCount;
    }
    public String getEmailId(){
        return this.emailId;
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
    public String getCustomerName(){
        return this.customerName;
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public String getCustomerId(){
        return customerId;
    }
    public String toString(){                       // used to replace displayCustomerDetails()
        return "Customer Id: "+ customerId+"\n"+
                "Name : "+customerName + "\n" +
                "Contact(phone/email): "+contactNumber +"\n\t\t"+emailId+"\n"+
                "Address : "+ address;
    }
    
}

