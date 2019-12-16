package com.example.myapplication.Data;

public class Hero {
    private int image;
    private String name;
    private String cl;
    private String origin;
    private int cost;

    public Hero(int image, String name, String cl,String origin, int cost) {
        this.image = image;
        this.name = name;
        this.cl = cl;
        this.origin = origin;
        this.cost = cost;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
