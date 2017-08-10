package com.example.android.tablebookingapp;

/**
 * Created by MuneebPC on 8/7/2017.
 */
public class LocationDurationDistance {


    public String distance;
    public String time;
    public String userKey;
    double  startLat ;
    double startLng ;

    public LocationDurationDistance(String userKey, double startLat, double startLng,String distance, String time) {
        this.distance = distance;
        this.time = time;
        this.userKey = userKey;
        this.startLat = startLat;
        this.startLng = startLng;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }
}
