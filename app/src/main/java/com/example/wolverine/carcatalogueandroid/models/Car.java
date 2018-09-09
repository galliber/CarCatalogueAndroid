package com.example.wolverine.carcatalogueandroid.models;

import java.io.Serializable;

public class Car implements Serializable{
    public int id;
    public String make;
    public String model;
    public  int power;
    public int cubicCapacity;
    public String imgUrl;

    public Car(int id, String make, String model, int power, int cubicCapacity,String imgUrl){
        this.id=id;
        this.make=make;
        this.model=model;
        this.power=power;
        this.cubicCapacity=cubicCapacity;
        this.imgUrl=imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getCubicCapacity() {
        return cubicCapacity;
    }
    public String getImgUrl(){
        return imgUrl;
    }
}