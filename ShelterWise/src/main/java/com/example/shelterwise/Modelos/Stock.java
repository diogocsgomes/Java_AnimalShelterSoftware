package com.example.shelterwise.Modelos;

public class Stock {
    public int id;
    public int quantity;
    public int category;

    public String name;
    public String description;
    public String expired_date;

    public Stock(){
        // Construtor vazio
    }

    public Stock(int id, int quantity, String name, String description, String expired_date, int category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.expired_date = expired_date;
        this.quantity = quantity;
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

    public String getExpiredDate(){return expired_date;}

    public void setExpiredDate(String expiredDate){this.expired_date = expiredDate;}

    public int getCategory(){
        return category;
    }

    public void setCategory(int category){
        this.category = category;
    }
}
