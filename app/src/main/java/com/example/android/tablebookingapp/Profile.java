package com.example.android.tablebookingapp;


public class Profile {

    public String uname;
    public String email;
    public String contact;
    public String userId;
    public String city;
    public String country;

    public Profile() {
    }

    public Profile(String userId, String uname, String email, String contact, String country, String city) {
        this.uname = uname;
        this.email = email;
        this.contact = contact;
        this.userId = userId;
        this.city = city;
        this.country = country;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
