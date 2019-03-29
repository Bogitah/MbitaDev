package com.example.mbitaferrydev;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mbitaferrydev.Database.DatabaseHelper;
import com.example.mbitaferrydev.Database.TicketCount;
import com.example.mbitaferrydev.Models.AdultsModel;
import com.example.mbitaferrydev.Models.BigAnimalModel;
import com.example.mbitaferrydev.Models.BigTruck;
import com.example.mbitaferrydev.customApplicationClass.CustomAppClass;
import com.google.android.material.navigation.NavigationView;
import com.nbbse.printapi.Printer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Number";
    ElegantNumberButton btnadult,btnBigAnumal,btnBigTruck;

    private DatabaseHelper db;
    private List<TicketCount> ticketsList = new ArrayList<>();
    int number_of_seates;
    Button btnprocess;
    CheckBox chkAdult,chkBigAnumal,chkBigTruck;

    int adultnum,biganimalnum,bigtrucknum;
    AdultsModel adult;
    BigAnimalModel bigAnimalModel;
    BigTruck bigTruck;
    int adultprice,biganimaprice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        bigAnimalModel = new BigAnimalModel();

        adult = new AdultsModel();






        Log.d("Inserted: ", db.loadTickets());
        Toast.makeText(getApplicationContext(), "Tickets Available " + db.loadTickets(), Toast.LENGTH_SHORT).show();


        btnBigTruck=findViewById(R.id.btnBigTruck);




        if (db.loadTickets().equals("")) {
            number_of_seates = Integer.valueOf("0");


        } else {
            number_of_seates = Integer.valueOf(db.loadTickets());

        }

        Log.d("Number of Tickets: ", String.valueOf(number_of_seates));


        btnprocess = findViewById(R.id.btnprocess);

        btnprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                printTicket();

            }
        });


        chkAdult = findViewById(R.id.chkAdult);
        chkAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    adult = new AdultsModel("Adult",adultnum,(adultnum*150));

                    adultprice=(adultnum * 150);



                }
            }
        });


        btnadult = findViewById(R.id.btnadult);
        btnadult.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adultnum = btnadult.getNumber();
            }
        });

        btnadult.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

                 adultnum = newValue;

            }
        });




        //Big Animal ************************************************************************************************************


        btnBigAnumal=findViewById(R.id.btnBigAnumal);
        chkBigAnumal= findViewById(R.id.chkBigAnumal);
        chkBigAnumal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    bigAnimalModel = new BigAnimalModel("Big Animal",biganimalnum,(biganimalnum *300));

                    biganimaprice=(biganimalnum*300);



                }
            }
        });


        btnBigAnumal.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBigAnumal.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

               biganimalnum = newValue;

            }
        });


        //End Big Animal *************************************************************************************************



        /// Big Truck------------------------------------------------------------

        chkBigTruck= findViewById(R.id.chkBigTruck);
        chkBigTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    bigTruck= new BigTruck("Big Truck",bigtrucknum,(bigtrucknum *300));




                }
            }
        });

        btnBigTruck.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBigTruck.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

                bigtrucknum = newValue;

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

        int total=(bigAnimalModel.getPrice() + adult.getPrice()+bigTruck.getPrice());



        Toast.makeText(getApplicationContext(), "Mobiwire Printing Ticket", Toast.LENGTH_LONG).show();
        Printer print = Printer.getInstance();
        print.printFormattedText();
        print.printText("------Mbita Ferry Services-----");
        print.printText("..........Mbita,KENYA..........");
        print.printText(".......Passenger Details.........");

        print.printText("Total: "+ total +" ksh");

        if(chkAdult.isChecked()){
            print.printText(adult.getTitle()+" "+adult.getNumber()+" "+adult.getPrice());

        }

        if(chkBigAnumal.isChecked()){
            print.printText(bigAnimalModel.getTitle()+" "+bigAnimalModel.getNumber()+" "+bigAnimalModel.getPrice());

        }

        if(chkBigTruck.isChecked()){
            print.printText(bigTruck.getTitle()+" "+bigTruck.getNumber()+" "+bigTruck.getPrice());

        }



        print.printText("Issued On :" + currentDateandTime);

        print.printBitmap(getResources().openRawResource(R.raw.payment_methods_old));
        print.printBitmap(getResources().openRawResource(R.raw.powered_by_mobiticket));
        print.printEndLine();
//


    }


}
