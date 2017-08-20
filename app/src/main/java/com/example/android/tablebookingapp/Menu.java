package com.example.android.tablebookingapp;


public class Menu {

    private String name;
    private String discription;
    private int image;
    private int price;
    private int quantity;



    public Menu() {
    }

    public Menu(String name, String discription, int image) {
        this.name = name;
        this.discription = discription;
        this.image = image;
    }

    public Menu(String name, String discription, int image, int price) {
        this.name = name;
        this.discription = discription;
        this.image = image;
        this.price = price;
    }

    public Menu(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public int getImage() {
        return image;
    }

    public int  getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
