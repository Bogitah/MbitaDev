package com.example.mbitaferrydev.Database;

public class TicketCount {


    private String total;

    public TicketCount(String total) {
        this.total = total;
    }

    public TicketCount(int i, String string) {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
