package com.example.shelterwise.Modelos;

public class Animal {
    public int id;
    public String birthDate;
    public String breed;
    public String comments ;
    public String furColor;
    public String furType;
    public String gender;
    public int kennelId;
    public String name;
    public String type;
    public double weight;

    public Animal() {
        // Construtor vazio
    }

    public Animal(int id, String birthDate, String breed, String comments, String furColor, String furType, String gender, int kennelId, String name, String type, double weight) {
        this.id = id;
        this.birthDate = birthDate;
        this.breed = breed;
        this.comments = comments;
        this.furColor = furColor;
        this.furType = furType;
        this.gender = gender;
        this.kennelId = kennelId;
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getFurType() {
        return furType;
    }

    public void setFurType(String furType) {
        this.furType = furType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getKennelId() {
        return kennelId;
    }

    public void setKennelId(int kennelId) {
        this.kennelId = kennelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}