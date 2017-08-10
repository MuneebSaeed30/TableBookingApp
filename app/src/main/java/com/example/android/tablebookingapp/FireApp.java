package com.example.android.tablebookingapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by MuneebPC on 7/29/2017.
 */
public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }
}
