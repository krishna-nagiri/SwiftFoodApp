package com.infy.models;

public class Order {
    private int OrderId;
    private String OrderedFoods;
    private double totalPrice;
    private String Status;
    private static int orderCount;

    // public Order(String OrderedFoods,double totalPrice,String statu)

    static{
        orderCount = 0;
    }
    public void setOrderId(int OrderId){
        this.OrderId = OrderId;
    }
    public int getOrderId(){
        return OrderId;
    }
     public void setOrderedFoods(String OrderedFoods){
        this.OrderedFoods = OrderedFoods;
    }
    public String getOrderedFoods(){
        return OrderedFoods;
    }
    public void settotalprice(int totalPrice){
        this.totalPrice = totalPrice;
    }
    public double gettotalPrice(){
        return totalPrice;
    }
    public void setStatus(String Status){
        this.Status = Status;
    }
    public String getStatus(){
        return Status;
    }

    

    public double CalculateTotalPrice(double unitPrice){
        
        double discount  = (unitPrice *(5))/100;
        this.totalPrice = unitPrice - discount;
        return this.totalPrice;
    }
}
