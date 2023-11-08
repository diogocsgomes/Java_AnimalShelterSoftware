package com.example.shelterwise.Modelos;

public class CategoryProduct {
    private int id;
    private String name;

    public CategoryProduct(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
