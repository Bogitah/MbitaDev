package com.example.mbitaferrydev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.example.mbitaferrydev.BaseUrl.ApiUrls;
import com.example.mbitaferrydev.Database.DatabaseHelper;
import com.example.mbitaferrydev.Database.ReffNumber;
import com.example.mbitaferrydev.Database.Ticket;
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
import com.example.mbitaferrydev.NetworkUtil.NetworkUtil;
import com.example.mbitaferrydev.PostObject.Request_body;
import com.example.mbitaferrydev.PostObject.Request_items;
import com.example.mbitaferrydev.customApplicationClass.CustomAppClass;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nbbse.printapi.Printer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DroidListener {

    private BroadcastReceiver MyReceiver = null;

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
    List<Ticket> tickets;
    private DatabaseHelper db;
    private TicketsSQLiteDatabaseHandler ticketsdb;

    private DroidNet mDroidNet;
    Map<String, Object> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        app = (CustomAppClass) getApplication();
        MyReceiver = new Internet_check_service();



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

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);

        map = new HashMap<String, Object>();


        Log.d("Inserted: ", db.loadTickets());
        Toast.makeText(getApplicationContext(), "Tickets Available " + db.loadTickets(), Toast.LENGTH_SHORT).show();

        // list all players
        tickets = ticketsdb.allTickets();

        Log.d("DB_Tickets_all:", tickets.toString());


        List<ReffNumber> allTags = ticketsdb.getAllReffs();


        btnBigTruck = findViewById(R.id.btnBigTruck);

        broadcastIntent();


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

                ReffNumber tref = ticketsdb.getRef(counter);
                for (ReffNumber tag : allTags) {
                    Log.d("Refs Name", tag.getRef_name());


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
                    reserve_ticket_online();                }


            }
        });


        chkAdult = findViewById(R.id.chkAdult);
        chkAdult.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {

                adult = new AdultsModel("Adult", adultnum, (adultnum * 150), ref_no);
                int cost = 150;
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
                bigAnimalTicket = new Ticket("Big Animal", biganimalnum, 300, date, ref_no);


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
                bigTruckTicket = new Ticket("Big Animal", bigtrucknum, 300, date, ref_no);


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
                childTicket = new Ticket("Child", childnum, 50, date, ref_no);


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
                LuggageTicket = new Ticket("Luggage", luggagenum, 60, date, ref_no);


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
                motoCycleTicket = new Ticket("Motor Cycle", motorCyclenum, 250, date, ref_no);


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
                    saloonCarticket = new Ticket("Saloon Car", saloonCarnum, 930, date, ref_no);


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
                    smallAnimalTicket = new Ticket("Small Animal", smallAnimalnum, 200, date, ref_no);


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
                    smallTruckTicket = new Ticket("Small Truck", smallTrucknum, 1740, date, ref_no);


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
                    stationicket = new Ticket("Station Wagon", stationWagonnum, 1160, date, ref_no);


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
                    TukTukTicket = new Ticket("Tuk Tuk", tuktuknum, 500, date, ref_no);


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


        if (Build.MODEL.equals("MobiPrint")) {


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

        } else {

            Toast.makeText(getApplicationContext(), "Printing Not Supported on this device", Toast.LENGTH_SHORT).show();
        }

    }


    private void reserve_ticket_online() {


        List<Request_items> ticket_items = new ArrayList<>();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        int total_items = adultnum + biganimalnum + bigtrucknum + childnum + luggagenum +
                motorCyclenum + othernum + saloonCarnum + smallAnimalnum + smallTrucknum + stationWagonnum + tuktuknum;
        Log.d("Total Items", String.valueOf(total_items));


        try {


            for (int x = 1; x <= total_items; x++) {
                ticket_items.add(new Request_items(

                        "1",
                        "2",
                        date,
                        "1",
                        "49",
                        "6A",
                        String.valueOf(x),
                        "6",
                        "0795890820",
                        "30170003",
                        "Oroni",
                        "brianoroni6@gmail.com",
                        "",
                        "Test User",
                        "10",
                        ref_no
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Request_body request_body = new Request_body(
                "emuswailit",
                "c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7",
                "1FBEAD9B-D9CD-400D-ADF3-F4D0E639CEE0",
                "BatchReserveSeats",
                ticket_items);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String requestBody1 = gson.toJson(request_body);

        Log.d("PostBody:", requestBody1);


        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public byte[] getBody() throws AuthFailureError {



                byte[] body = new byte[0];
                try {
                    body = requestBody1.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "Unable to gets bytes from JSON", e.fillInStackTrace());
                }
                return body;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);

                    Log.d("Time", String.valueOf(response.networkTimeMs));
                    Log.d("Headers", String.valueOf(response.headers));
                    Log.d("Raw Data", new String(response.data));


                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }

    private void reserve_update() {


        List<Request_items> request_items = new ArrayList<>();


        if (tickets.size() > 0) {

            for (Ticket obj : tickets) {

                int id = obj.getId();
                String ticket_type = obj.getTicket_type();
                int number = obj.getNumber();

                int cost = obj.getCost();
                String amount = String.valueOf(cost);
                String date = obj.getDate();
                String ref = obj.getRef_no();


                String textViewContents = ticket_type + ": " + number + " - " + cost + " Date: " + date + " Ref:" + ref;


                request_items.add(new Request_items(

                        app.getFrom(),
                        app.getTo(),
                        date,
                        "1",
                        "49",
                        "6A",
                        "1",
                        "6",
                        "0795890820",
                        "30170003",
                        ticket_type,
                        "brianoroni6@gmail.com",
                        "",
                        "Test User",
                        amount,
                        ref
                ));
            }


        } else {

            Toast.makeText(getApplicationContext(), "No records yet.", Toast.LENGTH_SHORT).show();

        }


        Request_body request_body = new Request_body(
                "emuswailit",
                "c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7",
                "1FBEAD9B-D9CD-400D-ADF3-F4D0E639CEE0",
                "BatchReserveSeats", request_items);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String requestBody = gson.toJson(request_body);

        Log.d("Body:", requestBody);


        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);

                ticketsdb.deleteAll();

                Toast.makeText(getApplicationContext(), "All Tickets Synced", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public byte[] getBody() throws AuthFailureError {
                byte[] body = new byte[0];
                try {
                    body = requestBody.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "Unable to gets bytes from JSON", e.fillInStackTrace());
                }
                return body;            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);

                    Log.d("Time", String.valueOf(response.networkTimeMs));
                    Log.d("Headers", String.valueOf(response.headers));
                    Log.d("Raw Data", new String(response.data));

                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
        unregisterReceiver(MyReceiver);

    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {


        if (isConnected) {




        } else {
        }

    }


    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }




    public class Internet_check_service extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = NetworkUtil.getConnectivityStatusString(context);
            if(status.isEmpty()) {
                status="No Internet Connection";
            }
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show();


//            if (tickets.size() > 0) {
            Toast.makeText(getApplicationContext(), "Tickets Syncing..", Toast.LENGTH_SHORT).show();
            reserve_update();

        }}
}