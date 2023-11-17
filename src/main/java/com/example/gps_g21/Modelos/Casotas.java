package com.example.gps_g21.Modelos;

public class Casotas {
    int id, max, num;

    public Casotas(){}

    public Casotas(int id, int max, int num){
        this.id=id;
        this.num=num;
        this.max=max;
    }


    public void setId(int id) {
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public void setMax(int max) {
        this.max=max;
    }
    public int getMax() {
        return max;
    }

    public void setNum(int num) {
        this.num=num;
    }
    public int getNum() {
        return num;
    }
}
