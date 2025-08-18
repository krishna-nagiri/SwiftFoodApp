package main.com.infy.models;

public class Customer {
	private  String customerId;
	private String customerName;
	private long contactNumber;
	private String address;
    private String emailId;
    private String password;

 
    // First time user.
    public Customer(String customerId,String customerName,long contactNumber,String address,String emailId,String password){
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.password = password;
        this.address = address;
        this.customerId  = customerId; 
    }
    // Setter Methods
    public void setAddress(String address){
        this.address = address;
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
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    // Getter Methods
    public String getCustomerId(){
        return customerId;
    }
    public String getCustomerName(){
        return this.customerName;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmailId(){
        return this.emailId;
    }
    public String toString(){                       // used to replace displayCustomerDetails()
        return "Customer Id: "+ customerId+"\n"+
                "Name : "+customerName + "\n" +
                "Contact(phone/email): "+contactNumber +"\n\t\t\t"+emailId+"\n"+
                "Address : "+ address+"\n\n\n";
    }
    
}

