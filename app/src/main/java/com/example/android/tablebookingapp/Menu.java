package com.example.android.tablebookingapp;


public class Menu {

    private String name;
    private String discription;
    private int image;





    public Menu() {
    }

    public Menu(String name, String discription,int image) {
        this.name = name;
        this.discription = discription;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    public String getDiscription() {
        return discription;
    }


}
