package com.example.mbitaferrydev.Models;

public class Manifests {
    String types;
    int number;
    int amount;

    public Manifests() {
    }
    public Manifests(String types, int number, int amount) {
        this.types = types;
        this.number = number;
        this.amount = amount;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
