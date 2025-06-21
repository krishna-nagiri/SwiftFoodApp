package com.infy.models;

public class Food {
    private String foodName;
    private String cusine;
    private String foodType;
    private int quantityAvaliable;
    private double unitPrice;

    public void setfoodName(String foodName){
        this.foodName = foodName;
    }
    public String getfoodName(){
        return this.foodName;
    }
    public void setcusine(String cusine){
        this.cusine = cusine;
    }
    public String getcusine(){
        return this.cusine;
    }
    public void setquantityAvaliable(int quantityAvaliable){
        this.quantityAvaliable = quantityAvaliable;
    }
    public int getquantityAvaliable(){
        return this.quantityAvaliable;
    }
    public void setfoodType(String foodType){
        this.foodType = foodType;
    }
    public String getfoodType(){
        return this.foodType;
    }
    public void setunitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public double getunitPrice(){
        return this.unitPrice;
    }
   public String toString(){
    return "Item : "+foodName+"\nCusine Type : "+cusine+"\nUnit Price : "+unitPrice;
   }

}
