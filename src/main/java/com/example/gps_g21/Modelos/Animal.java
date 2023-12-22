package com.example.gps_g21.Modelos;

public class Animal {
    public int id;
    public String image;
    public String birthDate;
    public String breed;
    public String comments ;
    public String furColor;
    public String furType;
    public String gender;
    public String kennelId;
    public String name;
    public String type;
    public double weight;

    public int healthStatus;

    public Animal() {
        // Construtor vazio
    }

    public Animal(int id, String image, String birthDate, String breed, String comments, String furColor, String furType, String gender, String kennelId, String name, String type, double weight, int healthStatus) {
        this.id = id;
        this.image = image;
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
        this.healthStatus = healthStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}

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

    public String getKennelId() {
        return kennelId;
    }

    public void setKennelId(String kennelId) {
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

    public double getHealthStatus() {return healthStatus;}

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", breed='" + breed + '\'' +
                ", comments='" + comments + '\'' +
                ", furColor='" + furColor + '\'' +
                ", furType='" + furType + '\'' +
                ", gender='" + gender + '\'' +
                ", kennelId=" + kennelId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }
}