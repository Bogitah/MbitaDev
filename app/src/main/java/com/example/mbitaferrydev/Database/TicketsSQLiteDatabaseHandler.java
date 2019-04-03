package com.example.mbitaferrydev.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class TicketsSQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TicketsDB";
    private static final String TABLE_NAME = "Tickets";
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_COST = "cost";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_ID, KEY_TYPE, KEY_NUMBER,
            KEY_COST, KEY_DATE};

    public TicketsSQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE Tickets ( id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, number INTEGER, cost INTEGER ,date TEXT )";

        db.execSQL(CREATION_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(Ticket ticket) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(ticket.getId()) });
        db.close();
    }

    public Ticket getTicket(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // h. limit
        Ticket ticket;
        try (Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null)) {

            if (cursor != null)
                cursor.moveToFirst();

            ticket = new Ticket();
            ticket.setId(cursor.getInt(0));
            ticket.setTicket_type(cursor.getString(1));
            ticket.setNumber(cursor.getInt(2));
            ticket.setCost(cursor.getInt(3));
            ticket.setDate(cursor.getString(4));
        }

        return ticket;
    }


    public List<Ticket> allTickets() {

        List<Ticket> tickets = new LinkedList<Ticket>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            Ticket ticket = null;

            if (cursor.moveToFirst()) {
                do {
                    ticket = new Ticket();
                    ticket.setId(cursor.getInt(0));
                    ticket.setTicket_type(cursor.getString(1));
                    ticket.setNumber(cursor.getInt(2));
                    ticket.setCost(cursor.getInt(3));
                    ticket.setDate(cursor.getString(4));

                    tickets.add(ticket);
                } while (cursor.moveToNext());
            }


            cursor.close();
            db.close();
        }


        return tickets;
    }


    public void addTicket(Ticket ticket) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, ticket.getTicket_type());
        values.put(KEY_NUMBER, ticket.getNumber());
        values.put(KEY_COST, ticket.getCost());
        values.put(KEY_DATE, ticket.getDate());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

}
