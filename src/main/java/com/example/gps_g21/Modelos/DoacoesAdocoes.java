package com.example.gps_g21.Modelos;

public class DoacoesAdocoes {
    public int id;
    public String nameAnimal;
    public String nameAdopter;
    public String adoptionDate;

    public DoacoesAdocoes() {
        // Construtor vazio
    }
    public DoacoesAdocoes(int id, String nameAnimal, String nameAdopter, String adoptionDate) {
        this.id = id;
        this.nameAnimal = nameAnimal;
        this.nameAdopter = nameAdopter;
        this.adoptionDate = adoptionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }

    public String getNameAdopter() {
        return nameAdopter;
    }

    public void setNameAdopter(String nameAdopter) {
        this.nameAdopter = nameAdopter;
    }

    public String getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(String adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
}
