package com.example.mbitaferrydev.Models;

public class SmallTruck {
    String title;
    int number;
    int price;

    public SmallTruck(String title, int number, int price) {
        this.title = title;
        this.number = number;
        this.price = price;
    }

    public SmallTruck() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
