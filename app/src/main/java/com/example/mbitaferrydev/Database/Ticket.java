package com.example.mbitaferrydev.Database;



public class Ticket {
    private int id;
    String ticket_type;
    int number, cost;
    String date;



    public  Ticket() {}


    public Ticket(String ticket_type, int number, int cost, String date) {
        this.ticket_type = ticket_type;
        this.number = number;
        this.cost = cost;
        this.date = date;
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
