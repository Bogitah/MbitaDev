package com.example.mbitaferrydev.PostObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request_body  {
    @SerializedName("username")
    String username;
    @SerializedName("api_key")

    String api_key;
    @SerializedName("hash")

    String hash;
    @SerializedName("action")

    String action;

    @SerializedName("ticket_items")

    List<Request_items> request_items;


    public Request_body(String username, String api_key, String hash, String action, List<Request_items> request_items) {
        this.username = username;
        this.api_key = api_key;
        this.hash = hash;
        this.action = action;
        this.request_items = request_items;
    }
}
