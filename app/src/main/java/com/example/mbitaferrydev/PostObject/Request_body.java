package com.example.mbitaferrydev.PostObject;

import java.util.List;

public class Request_body  {

    String username;
    String api_key;
    String hash;
    String action;

    List<Request_items> request_items;


    public Request_body(String username, String api_key, String hash, String action, List<Request_items> request_items) {
        this.username = username;
        this.api_key = api_key;
        this.hash = hash;
        this.action = action;
        this.request_items = request_items;
    }
}
