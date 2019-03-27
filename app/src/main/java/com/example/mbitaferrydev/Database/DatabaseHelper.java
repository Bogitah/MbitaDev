package com.example.mbitaferrydev.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TicketCount";
    private static final String TABLE_TICKETCOUNT = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_COUNT = "total";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TICKETCOUNT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COUNT + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKETCOUNT);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addCount(TicketCount contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COUNT, contact.getTotal()); // Contact Name

        // Inserting Row
        db.insert(TABLE_TICKETCOUNT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    TicketCount getCount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TICKETCOUNT, new String[] { KEY_ID,
                        KEY_COUNT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TicketCount contact = new TicketCount(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return contact;
    }

//     code to get all contacts in a list view



    public String loadTickets() {
        String result = "";
        String query = "SELECT  *  FROM " + TABLE_TICKETCOUNT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_1 = cursor.getString(1);
            result = String.valueOf(result_1);
        }
        cursor.close();
        db.close();
        return result;
    }


    // code to update the single contact
    public int updateContact(TicketCount contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COUNT, contact.getTotal());

        // updating row
        return db.update(TABLE_TICKETCOUNT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getTotal()) });
    }

    // Deleting single contact
    public void deleteContact(TicketCount contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TICKETCOUNT, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getTotal()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TICKETCOUNT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
