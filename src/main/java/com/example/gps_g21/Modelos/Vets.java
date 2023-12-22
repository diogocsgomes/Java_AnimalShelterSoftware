package com.example.gps_g21.Modelos;

public class Vets {

    int id, phone;
    String name, address, email;

    public Vets(){};

    public Vets(int id, int phone, String name, String address, String email) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }

    public int getPhone(){return phone;}
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail(){return email;}
    public void setEmail(String email) {
        this.email=email;
    }

    public String getAddress(){return address;}
    public void setAddress(String address) {
        this.address = address;
    }

}
