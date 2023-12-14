package com.example.gps_g21.Modelos;

public class DoacoesAdocoes {
    public int id;



    public int AdopterId;
    public String nameAnimal;
    public String nameAdopter;
    public String adoptionDate;
    public String donorName;
    public String donorPhone;
    public String donationDescription;

    public DoacoesAdocoes() {
        // Construtor vazio
    }
    public DoacoesAdocoes(int id, String nameAnimal, String nameAdopter, String adoptionDate, String donorName, String donorPhone, String donationDescription) {
        this.id = id;
        this.nameAnimal = nameAnimal;
        this.nameAdopter = nameAdopter;
        this.adoptionDate = adoptionDate;
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donationDescription = donationDescription;
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

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonationDescription() {
        return donationDescription;
    }

    public void setDonationDescription(String donationDescription) {
        this.donationDescription = donationDescription;
    }

    public int getAdopterId() {
        return AdopterId;
    }

    public void setAdopterId(int adopterId) {
        AdopterId = adopterId;
    }



}
