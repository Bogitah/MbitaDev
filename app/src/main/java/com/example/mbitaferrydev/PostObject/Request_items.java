package com.example.mbitaferrydev.PostObject;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Request_items {


    String from_city,
            to_city,
            travel_date,
            selected_vehicle,
            seater,
            selected_seat,
            selected_ticket_type,
            payment_method,
            phone_number,
            id_number,
            passenger_name,
            email_address,
            insurance_charge,
            served_by,
            amount_charged,
            reference_number;


    public Request_items(String from_city, String to_city, String travel_date,
                         String selected_vehicle, String seater, String selected_seat,
                         String selected_ticket_type, String payment_method, String phone_number,
                         String id_number, String passenger_name, String email_address,
                         String insurance_charge, String served_by, String amount_charged, String reference_number) {
        this.from_city = from_city;
        this.to_city = to_city;
        this.travel_date = travel_date;
        this.selected_vehicle = selected_vehicle;
        this.seater = seater;
        this.selected_seat = selected_seat;
        this.selected_ticket_type = selected_ticket_type;
        this.payment_method = payment_method;
        this.phone_number = phone_number;
        this.id_number = id_number;
        this.passenger_name = passenger_name;
        this.email_address = email_address;
        this.insurance_charge = insurance_charge;
        this.served_by = served_by;
        this.amount_charged = amount_charged;
        this.reference_number = reference_number;



    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public String getSelected_vehicle() {
        return selected_vehicle;
    }

    public void setSelected_vehicle(String selected_vehicle) {
        this.selected_vehicle = selected_vehicle;
    }

    public String getSeater() {
        return seater;
    }

    public void setSeater(String seater) {
        this.seater = seater;
    }

    public String getSelected_seat() {
        return selected_seat;
    }

    public void setSelected_seat(String selected_seat) {
        this.selected_seat = selected_seat;
    }

    public String getSelected_ticket_type() {
        return selected_ticket_type;
    }

    public void setSelected_ticket_type(String selected_ticket_type) {
        this.selected_ticket_type = selected_ticket_type;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getInsurance_charge() {
        return insurance_charge;
    }

    public void setInsurance_charge(String insurance_charge) {
        this.insurance_charge = insurance_charge;
    }

    public String getServed_by() {
        return served_by;
    }

    public void setServed_by(String served_by) {
        this.served_by = served_by;
    }

    public String getAmount_charged() {
        return amount_charged;
    }

    public void setAmount_charged(String amount_charged) {
        this.amount_charged = amount_charged;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }





}
