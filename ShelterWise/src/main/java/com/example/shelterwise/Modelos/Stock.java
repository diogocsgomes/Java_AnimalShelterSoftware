package com.example.shelterwise.Modelos;

public class Stock {
    int id, quantity;

    String name, description, expired_date, category;

    public Stock(){

    }

    public Stock(int id, int quantity, String name, String description, String expired_date, String category){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.expired_date = expired_date;
        this.category = category;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getExpiredDate(String expired_date){
        return expired_date;
    }

    public void setExpiredDate(String expiredDate){
        this.expired_date = expiredDate;
    }

    public String getCategory(String category){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
