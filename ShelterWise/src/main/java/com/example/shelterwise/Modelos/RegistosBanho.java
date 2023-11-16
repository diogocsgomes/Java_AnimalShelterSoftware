package com.example.shelterwise.Modelos;

import java.time.LocalDate;

public class RegistosBanho {
    private int id;
    private int animal_id;
    private String date;
    private String time;

    public RegistosBanho(int id, int animal_id, String date, String time) {
        this.id = id;
        this.animal_id = animal_id;
        this.date = date;
        this.time = time;
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        System.out.println("Animal ID set to: " + animal_id);
        this.animal_id = animal_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
