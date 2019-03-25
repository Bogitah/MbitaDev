package com.example.mbitaferrydev.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
    HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

    List<String> technology = new ArrayList<String>();
    technology.add("Beats sued for noise-cancelling tech");


    List<String> entertainment = new ArrayList<String>();
    entertainment.add("Toronto to open with The Judge");

    List<String> science = new ArrayList<String>();

    science.add("Secret of sandstone shapes revealed");

    expandableListDetail.put("Adult Standard", technology);
    expandableListDetail.put("Big AnimalS", entertainment);
    expandableListDetail.put("Big Truck", science);
    return expandableListDetail;
}

}
