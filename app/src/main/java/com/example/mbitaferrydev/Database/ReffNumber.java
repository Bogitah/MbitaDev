package com.example.mbitaferrydev.Database;

public class ReffNumber {

    int id;
    String ref_name;

    public ReffNumber(){}

    public ReffNumber(String ref_name) {
        this.ref_name = ref_name;
    }


    public ReffNumber(int id, String ref_name) {
        this.id = id;
        this.ref_name = ref_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef_name() {
        return ref_name;
    }

    public void setRef_name(String ref_name) {
        this.ref_name = ref_name;
    }
}
