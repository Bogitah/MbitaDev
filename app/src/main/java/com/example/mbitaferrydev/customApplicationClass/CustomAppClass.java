package com.example.mbitaferrydev.customApplicationClass;

import android.app.Application;

import com.droidnet.DroidNet;

public class CustomAppClass extends Application {

    String adult_price;
    String to, from;
    String username;
    String pasword;
    String api_key;
    String Hash_key;


    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
        DroidNet.init(this);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners();
    }



    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(String adult_price) {
        this.adult_price = adult_price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getHash_key() {
        return Hash_key;
    }

    public void setHash_key(String hash_key) {
        Hash_key = hash_key;
    }
}
