package com.example.mbitaferrydev.customApplicationClass;

import android.app.Application;

public class CustomAppClass extends Application {

    String adult_price;


    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
    }


    public String getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(String adult_price) {
        this.adult_price = adult_price;
    }
}
