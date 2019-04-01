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
import com.example.mbitaferrydev.Models.ChildModel;
import com.example.mbitaferrydev.Models.Luggage;
import com.example.mbitaferrydev.Models.MotorCycle;
import com.example.mbitaferrydev.Models.Others;
import com.example.mbitaferrydev.Models.SaloonCar;
import com.example.mbitaferrydev.Models.SmallAnimal;
import com.example.mbitaferrydev.Models.SmallTruck;
import com.example.mbitaferrydev.Models.StationWagon;
import com.example.mbitaferrydev.Models.TukTuk;
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
    ElegantNumberButton btnadult,btnBigAnumal,btnBigTruck,btnchild, btnLuggage, btnMotorCycle,
            btnOther, btnSaloonCar, btnSmallAnimal, btnSmallTruck, btnStationWagon, btnTuktuk;

    private DatabaseHelper db;
    private List<TicketCount> ticketsList = new ArrayList<>();
    int number_of_seates;
    Button btnprocess;
    CheckBox chkAdult,chkBigAnumal,chkBigTruck, chkChild, chkLuggage, chkMotorCycle,
            chkOther, chkSaloonCar, chkSmallAnimal, chkSmallTruck, chkStationWagon, chkTuktuk;

    int adultnum,biganimalnum,bigtrucknum, childnum, luggagenum,motorCyclenum,
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        bigAnimalModel = new BigAnimalModel();
        adult = new AdultsModel();
        childModel = new ChildModel();
        luggage = new Luggage();
        motorCycle = new MotorCycle();
        others = new Others();
        saloonCar = new SaloonCar();
        smallAnimal = new SmallAnimal();
        smallTruck = new SmallTruck();
        stationWagon = new StationWagon();
        tukTuk = new TukTuk();





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

                }else {
                    adult.setPrice(0);
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

                }else {
                bigAnimalModel.setPrice(0);
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
                else {
                    bigTruck.setPrice(0);
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

                  /// End Big Truck------------------------------------------------------------

/// Child-----------------------------------------------------------


        chkChild = findViewById(R.id.chkChild);
        chkChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    childModel = new ChildModel("Child",childnum,(childnum *50));
                }
                else {
                    childModel.setPrice(0);
                }
            }
        });
        btnchild = findViewById(R.id.btnchild);
        btnchild.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnchild.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

                childnum = newValue;

            }
        });


                            /// End Child------------------------------------------------------------


///Luggage ------------------------------------

        chkLuggage = findViewById(R.id.chkLuggage);
        chkLuggage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    luggage = new Luggage("Luggage",luggagenum,(luggagenum * 60));

                }
                else {
                    luggage.setPrice(0);
                }
            }
        });
        btnLuggage = findViewById(R.id.btnluggage);
        btnLuggage.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLuggage.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));

                luggagenum = newValue;

            }
        });
                              ////End Luggage---------------------------------

        ////MotorCycle---------------------
        chkMotorCycle = findViewById(R.id.chkMotor_cycle);
        chkMotorCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    motorCycle = new MotorCycle("Motor Cycle",motorCyclenum,(motorCyclenum * 250));
                }
                else {
                    motorCycle.setPrice(0);
                }
            }
        });
        btnMotorCycle = findViewById(R.id.btnmotor_cycle);
        btnMotorCycle.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMotorCycle.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, String.format("oldValue: %d   newValue: %d", oldValue, newValue));
                motorCyclenum = newValue;

            }
        });
                                ///End MotorCycle--------------------
        ////Others---------------------
        chkOther = findViewById(R.id.chkOther);
        chkOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    others = new Others("Other",othernum, 0);
                }
                else {
                    others.setPrice(0);
                }
            }
        });
        btnOther = findViewById(R.id.btnOther);
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
                                  ///End Other--------------------

        ////SaloonCar---------------------
        chkSaloonCar = findViewById(R.id.chkSalooncar);
        chkSaloonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    saloonCar = new SaloonCar ("Saloon Car",saloonCarnum,(saloonCarnum * 930));
                }
                else {
                    saloonCar.setPrice(0);
                }
            }
        });
        btnSaloonCar = findViewById(R.id.btnSalooncar);
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
                             ///End Saloon Car--------------------


        ////Small Animal---------------------

        chkSmallAnimal = findViewById(R.id.chkSmallAnimal);
        chkSmallAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    smallAnimal = new SmallAnimal("Small Animal",smallAnimalnum,(smallAnimalnum * 200));
                }
                else {
                    smallAnimal.setPrice(0);
                }
            }
        });
        btnSmallAnimal = findViewById(R.id.btnSmallAnimal);
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
                                             ///End Small Animal--------------------

        ////Small Truck---------------------
        chkSmallTruck = findViewById(R.id.chksmalltruck);
        chkSmallTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    smallTruck = new SmallTruck("Small Truck",smallTrucknum,(smallTrucknum * 1740));
                }
                else {
                    smallTruck.setPrice(0);
                }
            }
        });
        btnSmallTruck = findViewById(R.id.btnsmalltruck);
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
                                ///End Small Truck--------------------

        ////Station Wagon---------------------
        chkStationWagon = findViewById(R.id.chkstaionwagon);
        chkStationWagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    stationWagon = new StationWagon("Station wagon ",stationWagonnum,(stationWagonnum * 1160));
                }
                else {
                    stationWagon.setPrice(0);
                }
            }
        });
        btnStationWagon = findViewById(R.id.btnstaionwagon);
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
                                      ///End Station Wagon--------------------
        ////TukTuk---------------------
        chkTuktuk = findViewById(R.id.chktuktuk);
        chkTuktuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    tukTuk = new TukTuk("Tuk Tuk",tuktuknum,(tuktuknum * 500));

                }
                else {
                    tukTuk.setPrice(0);
                }
            }
        });
        btnTuktuk = findViewById(R.id.btntuktuk);
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

        int total=(bigAnimalModel.getPrice()+adult.getPrice()
                + bigTruck.getPrice()+childModel.getPrice()+luggage.getPrice()
               +motorCycle.getPrice()+ others.getPrice()+saloonCar.getPrice()+smallAnimal.getPrice()
               +smallTruck.getPrice()+stationWagon.getPrice()+tukTuk.getPrice());


        Toast.makeText(getApplicationContext(), "Mobiwire Printing Ticket", Toast.LENGTH_LONG).show();
        Printer print = Printer.getInstance();
        print.printFormattedText();
        print.printText("------Mbita Ferry Services-----");
        print.printText("..........Mbita,KENYA..........");
        print.printText(".......Passenger Details........");

        print.printText("Total: "+ total +" ksh\n");

        print.printText("Item      Quantity    Cost\n");


        if(chkAdult.isChecked()){
            print.printText(adult.getTitle()+"          "+adult.getNumber()+"       "+adult.getPrice());

        }

        if(chkBigAnumal.isChecked()){
            print.printText(bigAnimalModel.getTitle()+"     "+bigAnimalModel.getNumber()+"       "+bigAnimalModel.getPrice());

        }

        if(chkBigTruck.isChecked()){
            print.printText(bigTruck.getTitle()+"      "+bigTruck.getNumber()+"       "+bigTruck.getPrice());

        }
        if(chkChild.isChecked()){
            print.printText(childModel.getTitle()+"          "+childModel.getNumber()+"       "+childModel.getPrice());

        }
        if(chkLuggage.isChecked()){
            print.printText(luggage.getTitle()+"        "+luggage.getNumber()+"       "+luggage.getPrice());
        }
        if(chkMotorCycle.isChecked()){
            print.printText(motorCycle.getTitle()+"    "+motorCycle.getNumber()+"       "+motorCycle.getPrice());
        }
        if(chkOther.isChecked()){
            print.printText(others.getTitle()+"          "+others.getNumber()+"       "+others.getPrice());
        }
        if(chkSaloonCar.isChecked()){
            print.printText(saloonCar.getTitle()+"     "+saloonCar.getNumber()+"       "+saloonCar.getPrice());
        }
        if(chkSmallAnimal.isChecked()){
            print.printText(smallAnimal.getTitle()+"   "+smallAnimal.getNumber()+"       "+smallAnimal.getPrice());
        }
        if(chkSmallTruck.isChecked()){
            print.printText(smallTruck.getTitle()+"    "+smallTruck.getNumber()+"       "+smallTruck.getPrice());
        }
        if(chkStationWagon.isChecked()){
            print.printText(stationWagon.getTitle()+" "+stationWagon.getNumber()+"       "+stationWagon.getPrice());
        }
        if(chkTuktuk.isChecked()){
            print.printText(tukTuk.getTitle()+"        "+tukTuk.getNumber()+"       "+tukTuk.getPrice());
        }


        print.printText("Issued On :" + currentDateandTime);

        print.printBitmap(getResources().openRawResource(R.raw.payment_methods_old));
        print.printBitmap(getResources().openRawResource(R.raw.powered_by_mobiticket));
        print.printEndLine();
//


    }


}
