package com.example.mbitaferrydev;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mbitaferrydev.BaseUrl.ApiUrls;
import com.example.mbitaferrydev.Database.DatabaseHelper;
import com.example.mbitaferrydev.Database.ReffNumber;
import com.example.mbitaferrydev.Database.Ticket;
import com.example.mbitaferrydev.Database.TicketCount;
import com.example.mbitaferrydev.Database.TicketsSQLiteDatabaseHandler;
import com.example.mbitaferrydev.Models.AdultsModel;
import com.example.mbitaferrydev.Models.BigAnimalModel;
import com.example.mbitaferrydev.Models.BigTruck;
import com.example.mbitaferrydev.Models.ChildModel;
import com.example.mbitaferrydev.Models.Luggage;
import com.example.mbitaferrydev.Models.MotorCycle;
import com.example.mbitaferrydev.Models.Others;
import com.example.mbitaferrydev.Models.SaloonCar;
import com.example.mbitaferrydev.Models.SmallAnimal;
import com.example.mbitaferrydev.Models.SmallTruck;
import com.example.mbitaferrydev.Models.StationWagon;
import com.example.mbitaferrydev.Models.TukTuk;
import com.example.mbitaferrydev.customApplicationClass.CustomAppClass;
import com.google.android.material.navigation.NavigationView;
import com.nbbse.printapi.Printer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Number";
    ElegantNumberButton btnadult, btnBigAnumal, btnBigTruck, btnchild, btnLuggage, btnMotorCycle,
            btnOther, btnSaloonCar, btnSmallAnimal, btnSmallTruck, btnStationWagon, btnTuktuk;
    int number_of_seates;
    Button btnprocess;
    CheckBox chkAdult, chkBigAnumal, chkBigTruck, chkChild, chkLuggage, chkMotorCycle,
            chkOther, chkSaloonCar, chkSmallAnimal, chkSmallTruck, chkStationWagon, chkTuktuk;
    int adultnum, biganimalnum, bigtrucknum, childnum, luggagenum, motorCyclenum,
            othernum, saloonCarnum, smallAnimalnum, smallTrucknum, stationWagonnum, tuktuknum;
    AdultsModel adult;
    BigAnimalModel bigAnimalModel;
    BigTruck bigTruck;
    ChildModel childModel;
    Luggage luggage;
    MotorCycle motorCycle;
    Others others;
    SaloonCar saloonCar;
    SmallAnimal smallAnimal;
    SmallTruck smallTruck;
    StationWagon stationWagon;
    TukTuk tukTuk;
    TextView txtadlt, txtbiganiamal, txtbigtruck, txtchild, txtluggage, txtmotorcycle,
            txtother, txtsalooncar, txtsmallaminal, txtsmalltruck, txtstationwagon, txttuktuk;
    Ticket adultticket, bigAnimalTicket;
    Ticket bigTruckTicket, childTicket;
    Ticket LuggageTicket, motoCycleTicket;
    Ticket otherTicket, saloonCarticket;
    Ticket smallAnimalTicket, smallTruckTicket;
    Ticket TukTukTicket, stationicket;
    String date = new SimpleDateFormat("dd-MM-yyy", Locale.getDefault()).format(new Date());
    String ref_no;
    CustomAppClass app;
    private DatabaseHelper db;
    private List<TicketCount> ticketsList = new ArrayList<>();
    //
//    int ticketCounter=1;
//    int[] ticketCounterlist = new int[]{};
    private TicketsSQLiteDatabaseHandler ticketsdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        app = (CustomAppClass) getApplication();


        ticketsdb = new TicketsSQLiteDatabaseHandler(this);


        adult = new AdultsModel("Adult", 0, 0, ref_no);
        bigAnimalModel = new BigAnimalModel("Big Animal", 0, 0);
        childModel = new ChildModel("Child", 0, 0);
        luggage = new Luggage("Luggage", 0, 0);
        motorCycle = new MotorCycle("Motor Cycle", 0, 0);
        others = new Others("Other", 0, 0);
        saloonCar = new SaloonCar("Saloon car", 0, 0);
        smallAnimal = new SmallAnimal("Small Animal", 0, 0);
        smallTruck = new SmallTruck("Small Truck", 0, 0);
        stationWagon = new StationWagon("Station wagon", 0, 0);
        tukTuk = new TukTuk("Tuk tuk", 0, 0);
        bigTruck = new BigTruck("Big Truck", 0, 0);
        bigAnimalModel = new BigAnimalModel("Big Animal", 0, 0);


        txtadlt = findViewById(R.id.text_view);
        txtbiganiamal = findViewById(R.id.text_view_big_animal);
        txtbigtruck = findViewById(R.id.text_view_bg_truck);
        txtchild = findViewById(R.id.text_viewchild);
        txtluggage = findViewById(R.id.text_view_luggage);
        txtmotorcycle = findViewById(R.id.text_view_motor_cycle);
        txtother = findViewById(R.id.text_view_other);
        txtsalooncar = findViewById(R.id.text_view_btnSalooncar);
        txtsmallaminal = findViewById(R.id.txtsmallanimal);
        txtsmalltruck = findViewById(R.id.text_view_small_truck);
        txtstationwagon = findViewById(R.id.text_view_station_wagon);
        txttuktuk = findViewById(R.id.text_view_tuktuk);


        Log.d("Inserted: ", db.loadTickets());
        Toast.makeText(getApplicationContext(), "Tickets Available " + db.loadTickets(), Toast.LENGTH_SHORT).show();


        btnBigTruck = findViewById(R.id.btnBigTruck);


        if (db.loadTickets().equals("")) {
            number_of_seates = Integer.valueOf("0");


        } else {
            number_of_seates = Integer.valueOf(db.loadTickets());

        }

        Log.d("Number Items: ", String.valueOf(number_of_seates));


        btnprocess = findViewById(R.id.btnprocess);

        btnprocess.setOnClickListener(new View.OnClickListener() {
            int counter = 0;


            @Override
            public void onClick(View v) {

                List<ReffNumber> allTags = ticketsdb.getAllReffs();
                ReffNumber tref = ticketsdb.getRef(counter);


                for (int i = 1; i < allTags.size(); i++) {

                }

                if (counter <= allTags.size() && tref != null) {

                    ref_no = tref.getRef_name();
                    counter++;

                    printTicket();

                } else {

                    Toast.makeText(HomeActivity.this, "Reference Numbers finished,Go Online to load", Toast.LENGTH_SHORT).show();
                }


                chkAdult.setChecked(false);
                btnadult.setNumber(String.valueOf(0));

                chkBigAnumal.setChecked(false);
                btnBigAnumal.setNumber(String.valueOf(0));

                chkBigTruck.setChecked(false);
                btnBigTruck.setNumber(String.valueOf(0));

                chkChild.setChecked(false);
                btnchild.setNumber(String.valueOf(0));

                chkLuggage.setChecked(false);
                btnLuggage.setNumber(String.valueOf(0));

                chkMotorCycle.setChecked(false);
                btnMotorCycle.setNumber(String.valueOf(0));

                chkOther.setChecked(false);
                btnOther.setNumber(String.valueOf(0));

                chkSaloonCar.setChecked(false);
                btnSaloonCar.setNumber(String.valueOf(0));

                chkSmallAnimal.setChecked(false);
                btnSmallAnimal.setNumber(String.valueOf(0));

                chkSmallTruck.setChecked(false);
                btnSmallTruck.setNumber(String.valueOf(0));

                chkStationWagon.setChecked(false);
                btnStationWagon.setNumber(String.valueOf(0));

                chkTuktuk.setChecked(false);
                btnTuktuk.setNumber(String.valueOf(0));


                if (checkConnectivity()) {
                    reserve();

                }

            }
        });


        chkAdult = findViewById(R.id.chkAdult);
        chkAdult.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                adult = new AdultsModel("Adult", adultnum, (adultnum * 150), ref_no);
                int cost = (adultnum * 150);
                adultticket = new Ticket("Adult", adultnum, cost, date, ref_no);


            } else {
                adult.setPrice(0);
            }
        });


        btnadult = findViewById(R.id.btnadult);
        btnadult.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            String adultnum = btnadult.getNumber();
        });

        btnadult.setOnValueChangeListener((view, oldValue, newValue) -> {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

            adultnum = newValue;


        });


        chkAdult.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnadult.setVisibility(View.GONE);

                txtadlt.setText(adultnum + " Adult(s) selected");
            } else {
                btnadult.setVisibility(View.VISIBLE);
                txtadlt.setText("Adult");

            }
        });


        //Big Animal ************************************************************************************************************

        btnBigAnumal = findViewById(R.id.btnBigAnumal);
        chkBigAnumal = findViewById(R.id.chkBigAnumal);
        chkBigAnumal.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                bigAnimalModel = new BigAnimalModel("Big Animal", biganimalnum, (biganimalnum * 300));
                bigAnimalTicket = new Ticket("Big Animal", biganimalnum, (biganimalnum * 300), date, ref_no);


            } else {
                bigAnimalModel.setPrice(0);
            }
        });


        btnBigAnumal.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {

        });

        btnBigAnumal.setOnValueChangeListener((view, oldValue, newValue) -> {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

            biganimalnum = newValue;

        });

        chkBigAnumal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnBigAnumal.setVisibility(View.GONE);

                txtbiganiamal.setText(biganimalnum + "Animal(s) selected");
            } else {
                btnBigAnumal.setVisibility(View.VISIBLE);
                txtbiganiamal.setText("Big Animal");

            }
        });


        //End Big Animal *************************************************************************************************


        /// Big Truck------------------------------------------------------------

        chkBigTruck = findViewById(R.id.chkBigTruck);
        chkBigTruck.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                bigTruck = new BigTruck("Big Truck", bigtrucknum, (bigtrucknum * 300));
                bigTruckTicket = new Ticket("Big Animal", bigtrucknum, (bigtrucknum * 300), date, ref_no);

            } else {
                bigTruck.setPrice(0);
            }
        });

        btnBigTruck.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {

        });

        btnBigTruck.setOnValueChangeListener((view, oldValue, newValue) -> {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

            bigtrucknum = newValue;

        });
        chkBigTruck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnBigTruck.setVisibility(View.GONE);

                txtbigtruck.setText(bigtrucknum + " Truck(s) selected");
            } else {
                btnBigTruck.setVisibility(View.VISIBLE);
                txtbigtruck.setText("Big Truck");

            }
        });

        /// End Big Truck------------------------------------------------------------

/// Child-----------------------------------------------------------


        chkChild = findViewById(R.id.chkChild);
        chkChild.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                childModel = new ChildModel("Child", childnum, (childnum * 50));
                childTicket = new Ticket("Child", childnum, (childnum * 50), date, ref_no);

            } else {
                childModel.setPrice(0);
            }
        });
        btnchild = findViewById(R.id.btnchild);
        btnchild.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {

        });

        btnchild.setOnValueChangeListener((view, oldValue, newValue) -> {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

            childnum = newValue;

        });
        chkChild.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnchild.setVisibility(View.GONE);

                txtchild.setText(childnum + " Child(ren) selected");
            } else {
                btnchild.setVisibility(View.VISIBLE);
                txtchild.setText("Child");

            }
        });


        /// End Child------------------------------------------------------------


///Luggage ------------------------------------

        chkLuggage = findViewById(R.id.chkLuggage);
        chkLuggage.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                luggage = new Luggage("Luggage", luggagenum, (luggagenum * 60));
                LuggageTicket = new Ticket("Luggage", luggagenum, (luggagenum * 60), date, ref_no);


            } else {
                luggage.setPrice(0);
            }
        });
        btnLuggage = findViewById(R.id.btnluggage);
        btnLuggage.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {

        });

        btnLuggage.setOnValueChangeListener((view, oldValue, newValue) -> {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

            luggagenum = newValue;

        });
        chkLuggage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnLuggage.setVisibility(View.GONE);

                txtluggage.setText(luggagenum + " Luggage selected");
            } else {
                btnLuggage.setVisibility(View.VISIBLE);
                txtluggage.setText("Luggage");

            }
        });
        ////End Luggage---------------------------------

        ////MotorCycle---------------------
        chkMotorCycle = findViewById(R.id.chkMotor_cycle);
        chkMotorCycle.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                motorCycle = new MotorCycle("Motor Cycle", motorCyclenum, (motorCyclenum * 250));
                motoCycleTicket = new Ticket("Motor Cycle", motorCyclenum, (motorCyclenum * 250), date, ref_no);

            } else {
                motorCycle.setPrice(0);
            }
        });
        btnMotorCycle =

                findViewById(R.id.btnmotor_cycle);
        btnMotorCycle.setOnClickListener((ElegantNumberButton.OnClickListener) view ->

        {

        });

        btnMotorCycle.setOnValueChangeListener((view, oldValue, newValue) ->

        {
            Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
            motorCyclenum = newValue;

        });
        chkMotorCycle.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnMotorCycle.setVisibility(View.GONE);

                txtmotorcycle.setText(motorCyclenum + " MotorCycle(s) selected");
            } else {
                btnMotorCycle.setVisibility(View.VISIBLE);
                txtmotorcycle.setText("Motor Cycle");

            }
        });
        ///End MotorCycle--------------------

        ////Others---------------------
        chkOther =

                findViewById(R.id.chkOther);
        chkOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    others = new Others("Other", othernum, 0);

                    otherTicket = new Ticket("Other", othernum, (0), date, ref_no);

                } else {
                    others.setPrice(0);
                }
            }
        });
        btnOther =

                findViewById(R.id.btnOther);
        btnOther.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnOther.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                othernum = newValue;

            }
        });
        chkOther.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnOther.setVisibility(View.GONE);

                txtother.setText(othernum + " Other(s) selected");
            } else {
                btnOther.setVisibility(View.VISIBLE);
                txtother.setText("Other");

            }
        });
        ///End Other--------------------


        ////SaloonCar---------------------
        chkSaloonCar =

                findViewById(R.id.chkSalooncar);
        chkSaloonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    saloonCar = new SaloonCar("Saloon Car", saloonCarnum, (saloonCarnum * 930));
                    saloonCarticket = new Ticket("Saloon Car", saloonCarnum, (saloonCarnum * 930), date, ref_no);

                } else {
                    saloonCar.setPrice(0);
                }
            }
        });
        btnSaloonCar =

                findViewById(R.id.btnSalooncar);
        btnSaloonCar.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSaloonCar.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                saloonCarnum = newValue;

            }
        });
        chkSaloonCar.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnSaloonCar.setVisibility(View.GONE);

                txtsalooncar.setText(saloonCarnum + " Saloon car(s) selected");
            } else {
                btnSaloonCar.setVisibility(View.VISIBLE);
                txtsalooncar.setText("Saloon car");

            }
        });
        ///End Saloon Car--------------------


        ////Small Animal---------------------

        chkSmallAnimal =

                findViewById(R.id.chkSmallAnimal);
        chkSmallAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    smallAnimal = new SmallAnimal("Small Animal", smallAnimalnum, (smallAnimalnum * 200));
                    smallAnimalTicket = new Ticket("Small Animal", smallAnimalnum, (smallAnimalnum * 200), date, ref_no);

                } else {
                    smallAnimal.setPrice(0);
                }
            }
        });
        btnSmallAnimal =

                findViewById(R.id.btnSmallAnimal);
        btnSmallAnimal.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSmallAnimal.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                smallAnimalnum = newValue;

            }
        });
        chkSmallAnimal.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnSmallAnimal.setVisibility(View.GONE);

                txtsmallaminal.setText(smallAnimalnum + "Animal(s) selected");
            } else {
                btnSmallAnimal.setVisibility(View.VISIBLE);
                txtsmallaminal.setText("Small Animal");

            }
        });
        ///End Small Animal--------------------

        ////Small Truck---------------------
        chkSmallTruck =

                findViewById(R.id.chksmalltruck);
        chkSmallTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    smallTruck = new SmallTruck("Small Truck", smallTrucknum, (smallTrucknum * 1740));
                    smallTruckTicket = new Ticket("Small Truck", smallTrucknum, (smallTrucknum * 1740), date, ref_no);

                } else {
                    smallTruck.setPrice(0);
                }
            }
        });
        btnSmallTruck =

                findViewById(R.id.btnsmalltruck);
        btnSmallTruck.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSmallTruck.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                smallTrucknum = newValue;

            }
        });
        chkSmallTruck.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnSmallTruck.setVisibility(View.GONE);

                txtsmalltruck.setText(smallTrucknum + " Truck(s) selected");
            } else {
                btnSmallTruck.setVisibility(View.VISIBLE);
                txtsmalltruck.setText("Small Truck");

            }
        });
        ///End Small Truck--------------------

        ////Station Wagon---------------------
        chkStationWagon =

                findViewById(R.id.chkstaionwagon);
        chkStationWagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    stationWagon = new StationWagon("Station Wagon ", stationWagonnum, (stationWagonnum * 1160));
                    stationicket = new Ticket("Station Wagon", stationWagonnum, (stationWagonnum * 1160), date, ref_no);

                } else {
                    stationWagon.setPrice(0);
                }
            }
        });
        btnStationWagon =

                findViewById(R.id.btnstaionwagon);
        btnStationWagon.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStationWagon.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                stationWagonnum = newValue;

            }
        });
        chkStationWagon.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnStationWagon.setVisibility(View.GONE);

                txtstationwagon.setText(stationWagonnum + "Station wagon(s) selected");
            } else {
                btnStationWagon.setVisibility(View.VISIBLE);
                txtstationwagon.setText("Station Wagon");

            }
        });

        ///End Station Wagon--------------------
        ////TukTuk---------------------
        chkTuktuk =

                findViewById(R.id.chktuktuk);
        chkTuktuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    tukTuk = new TukTuk("Tuk Tuk", tuktuknum, (tuktuknum * 500));
                    TukTukTicket = new Ticket("Tuk Tuk", tuktuknum, (tuktuknum * 500), date, ref_no);


                } else {
                    tukTuk.setPrice(0);
                }
            }
        });
        btnTuktuk =

                findViewById(R.id.btntuktuk);
        btnTuktuk.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTuktuk.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                tuktuknum = newValue;

            }
        });
        chkTuktuk.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                btnTuktuk.setVisibility(View.GONE);

                txttuktuk.setText(tuktuknum + " Tuktuk(s) selected");
            } else {
                btnTuktuk.setVisibility(View.VISIBLE);
                txttuktuk.setText("Tuk Tuk");

            }
        });
        ///End Tuk Tuk--------------------

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
        } else if (id == R.id.manifest_id) {
            startActivity(new Intent(getApplicationContext(), ManifestActivity.class));
        } else if (id == R.id.payments_id) {
            startActivity(new Intent(getApplicationContext(), PaymentsActivity.class));
        } else if (id == R.id.search_payments_id) {
            startActivity(new Intent(getApplicationContext(), SearchPaymentsActivity.class));
        } else if (id == R.id.viewlocaldata) {
            startActivity(new Intent(getApplicationContext(), LocalDataActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void printTicket() {
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        int total = (bigAnimalModel.getPrice() + adult.getPrice()
                + bigTruck.getPrice() + childModel.getPrice() + luggage.getPrice()
                + motorCycle.getPrice() + others.getPrice() + saloonCar.getPrice() + smallAnimal.getPrice()
                + smallTruck.getPrice() + stationWagon.getPrice() + tukTuk.getPrice());


        Toast.makeText(getApplicationContext(), "Mobiwire Printing Ticket", Toast.LENGTH_LONG).show();
        Printer print = Printer.getInstance();
        print.printFormattedText();
        print.printText("------Mbita Ferry Services-----");
        print.printText("..........Mbita,KENYA..........");
        print.printText(".......Passenger Details........");

        print.printText("Total: " + total + " ksh");

        print.printText("Ticket Ref: " + ref_no);

        print.printText("Item      Quantity    Cost\n");


        if (chkAdult.isChecked()) {
            print.printText(adult.getTitle() + "          " + adult.getNumber() + "       " + adult.getPrice());
            if (!checkConnectivity()) {
                ticketsdb.addTicket(adultticket);


            } else {


            }


        }

        if (chkBigAnumal.isChecked()) {
            print.printText(bigAnimalModel.getTitle() + "     " + bigAnimalModel.getNumber() + "       " + bigAnimalModel.getPrice());

            if (!checkConnectivity()) {

                ticketsdb.addTicket(bigAnimalTicket);

            }

        }

        if (chkBigTruck.isChecked()) {
            print.printText(bigTruck.getTitle() + "      " + bigTruck.getNumber() + "       " + bigTruck.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(bigTruckTicket);
            }


        }
        if (chkChild.isChecked()) {
            print.printText(childModel.getTitle() + "          " + childModel.getNumber() + "       " + childModel.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(childTicket);

            }

        }
        if (chkLuggage.isChecked()) {
            print.printText(luggage.getTitle() + "        " + luggage.getNumber() + "       " + luggage.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(LuggageTicket);
            }

        }
        if (chkMotorCycle.isChecked()) {
            print.printText(motorCycle.getTitle() + "    " + motorCycle.getNumber() + "       " + motorCycle.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(motoCycleTicket);
            }

        }
        if (chkOther.isChecked()) {
            print.printText(others.getTitle() + "          " + others.getNumber() + "       " + others.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(otherTicket);
            }

        }
        if (chkSaloonCar.isChecked()) {
            print.printText(saloonCar.getTitle() + "     " + saloonCar.getNumber() + "       " + saloonCar.getPrice());

            if (!checkConnectivity()) {
                ticketsdb.addTicket(saloonCarticket);
            }


        }
        if (chkSmallAnimal.isChecked()) {
            print.printText(smallAnimal.getTitle() + "   " + smallAnimal.getNumber() + "       " + smallAnimal.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(smallAnimalTicket);
            }


        }
        if (chkSmallTruck.isChecked()) {
            print.printText(smallTruck.getTitle() + "    " + smallTruck.getNumber() + "       " + smallTruck.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(smallTruckTicket);
            }


        }
        if (chkStationWagon.isChecked()) {
            print.printText(stationWagon.getTitle() + " " + stationWagon.getNumber() + "       " + stationWagon.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(stationicket);
            }


        }
        if (chkTuktuk.isChecked()) {
            print.printText(tukTuk.getTitle() + "        " + tukTuk.getNumber() + "       " + tukTuk.getPrice());
            if (!checkConnectivity()) {

                ticketsdb.addTicket(TukTukTicket);
            }

        }


        print.printText("Issued On :" + currentDateandTime);
        print.printBitmap(getResources().openRawResource(R.raw.payment_methods_old));
        print.printBitmap(getResources().openRawResource(R.raw.powered_by_mobiticket));
        print.printEndLine();

    }

    private void reserve() {

        ProgressDialog pd = new ProgressDialog(HomeActivity.this);
        pd.setMessage("Reserving ...");
        pd.show();


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        RequestQueue reserverequestQueue = Volley.newRequestQueue(HomeActivity.this);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", app.getUsername());
        params.put("api_key", app.getApi_key());
        params.put("action", "ReserveSeats");
        params.put("from_city", app.getFrom());
        params.put("to_city", app.getTo());
        params.put("travel_date", date);
        params.put("hash", app.getHash_key());
        params.put("selected_vehicle", "3");
        params.put("seater", "491");
        params.put("selected_ticket_type", "8");
        params.put("payment_method", "1");
        params.put("selected_seat", "100");
        params.put("phone_number", "0702357053");
        params.put("id_number", "31947982");
        params.put("passenger_name", "Brian");
        params.put("email_address", "brianoroni6@gmail.com");
        params.put("insurance_charge", "");
        params.put("served_by", "Oroni");
        params.put("amount_charged", "10");
        params.put("reference_number", ref_no);


        JsonObjectRequest req = new JsonObjectRequest(ApiUrls.apiUrl, new JSONObject(params),
                response -> {
                    try {

                        Log.d("Response: ", response.toString(4));


                        if (response.getInt("response_code") == 0) {
                            JSONArray message = response.getJSONArray("ticket_message");

                            pd.dismiss();

                            Toast.makeText(getApplicationContext(), response.getString("response_message"), Toast.LENGTH_LONG).show();


                            for (int i = 0; i < message.length(); i++) {
                                JSONObject jsonObject1 = message.getJSONObject(i);
                                String ticket_mesaage = jsonObject1.getString("name");


                            }

                            JSONArray jsonArray = response.getJSONArray("ticket");


                        } else {
                            Toast.makeText(getApplicationContext(), response.getString("response_message"), Toast.LENGTH_LONG).show();
                            pd.dismiss();


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Exception", e.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley Error:", error.toString());


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=utf-8";
            }


        };

        reserverequestQueue.add(req);


    }


    private boolean checkConnectivity() {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            Toast.makeText(getApplicationContext(), "No Internet Connection...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }


}
