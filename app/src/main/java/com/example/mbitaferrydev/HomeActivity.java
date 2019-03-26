package com.example.mbitaferrydev;

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

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Number";
    ElegantNumberButton btnadult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//
//        editText = findViewById(R.id.numberEditText);
//
//        upButton = findViewById(R.id.upButton);
//        upButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
////                downButton
////                        .setBackgroundResource(R.drawable.timepicker_down_normal);
////                upButton.setBackgroundResource(R.drawable.timepicker_up_pressed);
//
////                values=Integer.valueOf(editText.getText().toString());
//                values += 1;
//
//                editText.setText("" + values);
//
//
//            }
//        });
//
//        downButton = findViewById(R.id.downButton);
//        downButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                downButton
////                        .setBackgroundResource(R.drawable.timepicker_down_pressed);
////                upButton.setBackgroundResource(R.drawable.timepicker_up_normal);
////                values=Integer.valueOf(editText.getText().toString());
//
//
//                values -= 1;
//
//                if (values < 0) {
//
//
//                    editText.setText("" + 1);
//                } else {
//                    editText.setText("" + values);
//
//                }
//
//
//                editText.setText(values + "");
//            }
//        });



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
            // Handle the camera action
        } else if (id == R.id.manifest_id) {

        } else if (id == R.id.search_payments_id) {

        } else if (id == R.id.change_password_id) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
