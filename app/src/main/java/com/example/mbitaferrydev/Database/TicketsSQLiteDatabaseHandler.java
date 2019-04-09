package com.example.mbitaferrydev.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TicketsSQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TicketsDB";


    private static final String TABLE_NAME = "Tickets";
    private static final String TABLE_REFRENCES = "Refs";



    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_COST = "cost";
    private static final String KEY_DATE = "date";
    private static final String KEY_REF = "ref";




    private static final String KEY_REF_ID = "id";
    private static final String KEY_REF_NAME = "ref_name";

    private static final String[] COLUMNS = {KEY_ID, KEY_TYPE, KEY_NUMBER,
            KEY_COST, KEY_DATE,KEY_REF};

    public TicketsSQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE Tickets ( id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, number INTEGER, cost INTEGER ,date TEXT,ref TEXT )";

        String CREATION_REF_TABLE = "CREATE TABLE Refs ( id INTEGER PRIMARY KEY AUTOINCREMENT, ref_name TEXT)";


        db.execSQL(CREATION_TABLE);
        db.execSQL(CREATION_REF_TABLE);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFRENCES);

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
            ticket.setRef_no(cursor.getString(5));
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
                    ticket.setRef_no(cursor.getString(5));


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
        values.put(KEY_REF,ticket.getRef_no());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }



    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


    public int getSumAdults(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Adults FROM %s WHERE %s ='Adult'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Adults"));
        return sum;
    }


    public int getSumBigANimals(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Animals FROM %s WHERE %s ='Big Animal'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Animals"));
        return sum;
    }



    public int getSumBigTrucks(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Big_Tracks FROM %s WHERE %s ='Big Truck'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Big_Tracks"));
        return sum;
    }


    public int getSumChildren(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_children FROM %s WHERE %s ='Child'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_children"));
        return sum;
    }



    public int getSumLuggage(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Luggage FROM %s WHERE %s ='Luggage'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Luggage"));
        return sum;
    }

    public int getSumMotoCycles(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_motobikes FROM %s WHERE %s ='Moto Cycle'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_motobikes"));
        return sum;
    }

    public int getSumOther(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_other FROM %s WHERE %s ='Other'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_other"));
        return sum;
    }




    public int getSumSaloonCar(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Saloon FROM %s WHERE %s ='Saloon Car'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Saloon"));
        return sum;
    }



    public int getSumSmallAnimal(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_small_animal FROM %s WHERE %s ='Small Animal'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_small_animal"));
        return sum;
    }



    public int getSumSmallTruck(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Small_truck FROM %s WHERE %s ='Small Truck'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Small_truck"));
        return sum;
    }


    public int getSumStationWagon(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_Small_station_wagon FROM %s WHERE %s ='Station Wagon'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_Small_station_wagon"));
        return sum;
    }

    public int getSumTukTuk(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s)  AS Total_tuktuk FROM %s WHERE %s ='Tuk Tuk'",KEY_NUMBER,TABLE_NAME,KEY_TYPE);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_tuktuk"));
        return sum;
    }

    public int getTotalCollection(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s) as Total FROM %s",KEY_COST,TABLE_NAME);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total"));
        return sum;
    }


    public int getTotalSeatsUsed(){

        int sum=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(%s) as Total_seats FROM %s",KEY_NUMBER,TABLE_NAME);
        Cursor cursor=db.rawQuery(sumQuery,null);
        if(cursor.moveToFirst())
            sum= cursor.getInt(cursor.getColumnIndex("Total_seats"));
        return sum;
    }







    public void  createRef(ReffNumber reffNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REF_ID, reffNumber.getId());
        values.put(KEY_REF_NAME, reffNumber.getRef_name());


        // insert row

      db.insertWithOnConflict(TABLE_REFRENCES, null, values, SQLiteDatabase.CONFLICT_IGNORE);


        db.close();

    }



    /**
     * getting all reffs
     * */
    public List<ReffNumber> getAllReffs() {
        List<ReffNumber> tags = new ArrayList<ReffNumber>();
        String selectQuery = "SELECT  * FROM " + TABLE_REFRENCES;

        Log.e("Reffs:", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ReffNumber t = new ReffNumber();
                t.setId(c.getInt((c.getColumnIndex(KEY_REF_ID))));
                t.setRef_name(c.getString(c.getColumnIndex(KEY_REF_NAME)));

                // adding to tags list
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }



    public ReffNumber getRef(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_REFRENCES + " WHERE "
                + KEY_REF_ID + " = " + id;

        Log.e("Query:", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        ReffNumber td = new ReffNumber();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setRef_name((c.getString(c.getColumnIndex(KEY_REF_NAME))));

        return td;

    }



}
