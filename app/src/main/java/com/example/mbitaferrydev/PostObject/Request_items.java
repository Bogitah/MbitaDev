package com.example.mbitaferrydev.PostObject;

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
}
