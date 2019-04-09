package com.example.mbitaferrydev.Models;

public class AdultsModel {
    String title;
    int number;
    int price;

    String ref_no;

    public AdultsModel() {

    }

    public AdultsModel(String title, int number, int price, String ref_no) {
        this.title = title;
        this.number = number;
        this.price = price;
        this.ref_no = ref_no;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
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
