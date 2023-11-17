package com.example.gps_g21.Modelos;

public class Users {

    int id, phone, nif, iban, role;
    String nome, date_birth, address, email;
    boolean active;

    public Users(){};

    public Users(int id, int phone, int nif, int iban, int role, String nome, String date_birth, String address, String email, boolean active) {
        this.id = id;
        this.date_birth = date_birth;
        this.phone = phone;
        this.nif = nif;
        this.iban = iban;
        this.role = role;
        this.nome = nome;
        this.address = address;
        this.email = email;
        this.active = active;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getNome(){return nome;}
    public void setName(String nome) {
        this.nome = nome;
    }

    public String getDate_birth(){return date_birth;}
    public void setBirth(String dateBirth) {
        this.date_birth = dateBirth;
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

    public int getNif(){return nif;}
    public void setNif(int nif) {
        this.nif = nif;

    }

    public boolean getActive(){return active;}
    public void setActive(boolean active) {
        this.active = active;
    }


}
