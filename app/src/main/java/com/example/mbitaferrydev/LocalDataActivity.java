package com.example.mbitaferrydev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mbitaferrydev.Database.Ticket;
import com.example.mbitaferrydev.Database.TicketsSQLiteDatabaseHandler;

import java.util.List;

public class LocalDataActivity extends AppCompatActivity {

    private TicketsSQLiteDatabaseHandler ticketsdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);

        ticketsdb = new TicketsSQLiteDatabaseHandler(this);



        // list all players
        List<Ticket> tickets = ticketsdb.allTickets();

        Log.d("DB_Ticks_all:",tickets.toString());


//        if (tickets != null) {
//            String[] itemsNames = new String[tickets.size()];
//
//            for (int i = 0; i < tickets.size(); i++) {
//                itemsNames[i] = tickets.get(i).toString();
//            }
//
//            // display like string instances
//            ListView list = (ListView) findViewById(R.id.ticketlist);
//            list.setAdapter(new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1, android.R.id.text1, itemsNames));
//
//        }


        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        if (tickets.size() > 0) {

            for (Ticket obj : tickets) {

                int id = obj.getId();
                String ticket_type = obj.getTicket_type();
                int number = obj.getNumber();

                int cost = obj.getCost();
                String  date = obj.getDate();


                String textViewContents = ticket_type + " - " + number +" - "+ cost +" - "+ date;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(8, 10, 8, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
