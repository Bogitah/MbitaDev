package com.example.mbitaferrydev;

<<<<<<< HEAD
import android.content.ClipData;
import android.content.Intent;
=======
import android.os.Build;
>>>>>>> d6c5b13da2b37036c5b06f7b34f54b3ea4658ba4
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mbitaferrydev.Database.DatabaseHelper;
import com.example.mbitaferrydev.Database.TicketCount;
import com.nbbse.printapi.Printer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Number";
    ElegantNumberButton btnadult;

    private DatabaseHelper db;
    private List<TicketCount> ticketsList = new ArrayList<>();
    int number_of_seates;
    Button btnprocess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);


        Log.d("Inserted: ", db.loadTickets());
        Toast.makeText(getApplicationContext(), "Tickets Available " + db.loadTickets(), Toast.LENGTH_SHORT).show();

        number_of_seates = Integer.valueOf(db.loadTickets());
        Log.d("Number of Tickets: ", String.valueOf(number_of_seates));


        btnprocess=findViewById(R.id.btnprocess);

        btnprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printTicket();
            }
        });


        btnadult = (ElegantNumberButton) findViewById(R.id.btnadult);
        btnadult.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = btnadult.getNumber();
            }
        });

        btnadult.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.search_tickets_id) {
            startActivity(new Intent(getApplicationContext(), SearchTicketActivity.class));

            // Handle the camera action
        } else if (id == R.id.manifest_id) {
            startActivity(new Intent(getApplicationContext(), ManifestActivity.class));
        } else if (id == R.id.payments_id) {
            startActivity(new Intent(getApplicationContext(), PaymentsActivity.class));
        } else if (id == R.id.search_payments_id) {
            startActivity(new Intent(getApplicationContext(), SearchPaymentsActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void printTicket() {
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (Build.MODEL.equals("MobiPrint")) {


            Toast.makeText(getApplicationContext(), "Mobiwire Printing Ticket", Toast.LENGTH_LONG).show();
            Printer print = Printer.getInstance();
            print.printFormattedText();
//                    print.printBitmap(getResources().openRawResource(R.raw.ena_coach_logo24bit));
            print.printText("-----------Mbita Ferry----------");
            print.printText("--------PO BOX 152-40202-------");
            print.printText("..........Mbita,KENYA..........");
            print.printText("......Passenger Details.........");
//                    print.printText("Name: " + name);
//                    print.printText("Ref No:" + json_obj.getString("merchant_transaction_id"));
//                    print.printText("Phone No:" + phone);
//                    print.printText("Seat:" +seat);
//                    print.printText("Fare: Ksh." + json_obj.getString("fare"));
//                    print.printText("................................");
//                    print.printText("......Vehicle Details.........");
//                    print.printText("Vehicle:" + json_obj.getString("bus"));
//                    print.printText("Route:" + json_obj.getString("route"));
//                    print.printText("Travel Date: " + json_obj.getString("travel_date"));
//                    print.printText("................................");
                    print.printText("Issued On :" + currentDateandTime);
//                    print.printText("Issued by :" + app.getLogged_user());
//                    print.printBitmap(getResources().openRawResource(R.raw.payment_methods_old));
//                    print.printBitmap(getResources().openRawResource(R.raw.powered_by_mobiticket));
            print.printEndLine();


        }


    }


}
