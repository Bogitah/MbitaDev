package com.example.mbitaferrydev;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mbitaferrydev.Database.ReffNumber;
import com.example.mbitaferrydev.Database.Ticket;
import com.example.mbitaferrydev.Database.TicketsSQLiteDatabaseHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import androidx.appcompat.app.AppCompatActivity;

public class LocalDataActivity extends AppCompatActivity {

    private TicketsSQLiteDatabaseHandler ticketsdb;

    private static SQLiteDatabase db;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);

        ticketsdb = new TicketsSQLiteDatabaseHandler(this);


        // list all players
        List<Ticket> tickets = ticketsdb.allTickets();

        Log.d("DB_Ticks_all:", tickets.toString());

        int num_of_adults = ticketsdb.getSumAdults();
        int totalcollection = ticketsdb.getTotalCollection();

        int sum_big_tracks = ticketsdb.getSumBigTrucks();
        int sum_big_animals = ticketsdb.getSumBigANimals();

        int sum_children = ticketsdb.getSumChildren();

        int TotalSeatsUsed = ticketsdb.getTotalSeatsUsed();


        int Small_animal = ticketsdb.getSumSmallAnimal();

        int small_truck = ticketsdb.getSumSmallTruck();

        int tuk_tuk = ticketsdb.getSumTukTuk();

        int moto_bike = ticketsdb.getSumMotoCycles();

        int others = ticketsdb.getSumOther();

        int saloon_cars = ticketsdb.getSumSaloonCar();

        int station_wagon = ticketsdb.getSumStationWagon();

        int luggage = ticketsdb.getSumLuggage();

        int summary_by_ref = ticketsdb.getTotalOfAllRefLevels();


        Log.d("Adults:", String.valueOf(num_of_adults));
        Log.d("Big Animals:", String.valueOf(sum_big_animals));
        Log.d("Big Tracks:", String.valueOf(sum_big_tracks));
        Log.d("children:", String.valueOf(sum_children));
        Log.d("Luggage:", String.valueOf(luggage));
        Log.d("Motor Cycle:", String.valueOf(moto_bike));
        Log.d("Others:", String.valueOf(others));
        Log.d("Saloon Car:", String.valueOf(saloon_cars));
        Log.d("Small Animal:", String.valueOf(Small_animal));
        Log.d("Small Truck:", String.valueOf(small_truck));
        Log.d("Station Wagon:", String.valueOf(station_wagon));
        Log.d("Tuk Tuks:", String.valueOf(tuk_tuk));
        Log.d("total collection:", String.valueOf(totalcollection));

        Log.d("total seats_used:", String.valueOf(TotalSeatsUsed));

        Log.d("summary_by_ref:", String.valueOf(summary_by_ref));

        List<ReffNumber> allTags = ticketsdb.getAllReffs();
        for (ReffNumber tag : allTags) {
            Log.d("Refs Name", tag.getRef_name());
        }

        LinearLayout linearLayoutRecords = findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        if (tickets.size() > 0) {

            for (Ticket obj : tickets) {

                int id = obj.getId();
                String ticket_type = obj.getTicket_type();
                int number = obj.getNumber();

                int cost = obj.getCost();
                String date = obj.getDate();
                String ref = obj.getRef_no();


                String textViewContents = ticket_type + ": " + number + " - " + cost + " Date: " + date +" Ref:" +ref;

                TextView textViewStudentItem = new TextView(this);
                textViewStudentItem.setPadding(8, 10, 8, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
            }

        } else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }





    }

}
