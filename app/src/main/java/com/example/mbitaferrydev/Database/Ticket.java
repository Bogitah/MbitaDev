package com.example.mbitaferrydev.Database;



public class Ticket {
    private int id;
    String ticket_type;
    int number, cost;
    String date;
    String ref_no;



    public  Ticket() {}


    public Ticket(int id, String ticket_type, int number, int cost, String date, String ref_no) {
        this.id = id;
        this.ticket_type = ticket_type;
        this.number = number;
        this.cost = cost;
        this.date = date;
        this.ref_no = ref_no;
    }

    public Ticket(String ticket_type, int number, int cost, String date, String ref_no) {

        this.ticket_type = ticket_type;
        this.number = number;
        this.cost = cost;
        this.date = date;
        this.ref_no = ref_no;
    }


    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int   number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
