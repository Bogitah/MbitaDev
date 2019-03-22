package com.example.mbitaferrydev;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mbitaferrydev.BaseUrl.ApiUrls;
import com.example.mbitaferrydev.CustomAdapters.FerryRouteCardArrayAdapter;
import com.example.mbitaferrydev.Models.Routes;
import com.orhanobut.simplelistview.SimpleListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeInitialActivity extends AppCompatActivity {

    final Context context = this;


    private static final String TAG = "CardListActivity";

    private FerryRouteCardArrayAdapter cardArrayAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_initial);
//        mainListView = findViewById(R.id.ferryRoutesId);
//        ferryList = new ArrayList<String>();
//        mainListView.setHeaderView(R.layout.header);

        listView = (ListView) findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new FerryRouteCardArrayAdapter(getApplicationContext(), R.layout.list_item_card);


        loadFerryRoutes();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String selected = String.valueOf(listView.indexOfChild(view));

//                String route = cardArrayAdapter.getItem(position).getName();

//                Toast.makeText(getApplicationContext(),selected, Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.login_dialog);
                dialog.setTitle("      Account Login");
                EditText username,password;
                username=dialog.findViewById(R.id.editTextusername);
                password=dialog.findViewById(R.id.editTextpassword);

                Button dialogButton = (Button) dialog.findViewById(R.id.btnlogin);


                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                    }
                });
                dialog.show();


            }});

    }



    private void loadFerryRoutes() {


        RequestQueue ferryrequests = Volley.newRequestQueue(HomeInitialActivity.this);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", "emuswailit");
        params.put("api_key", "c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7");
        params.put("action", "Schedule");
        params.put("hash", "1FBEAD9B-D9CD-400D-ADF3-F4D0E639CEE0");
        params.put("travel_date", "23-01-2019");


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, ApiUrls.allferyapiUrl, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("response_code") == 0) {
                                JSONArray jsonArray = response.getJSONArray("bus");
                                Log.i("Response:", jsonArray.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String ferry_route = jsonObject1.getString("route");

                                    String name=ferry_route.substring(8);


                                    Log.d("Ferry Routes: ", ferry_route);

                                    Routes card = new Routes(name);

                                    cardArrayAdapter.add(card);

                                }


                            } else {

                                Toast.makeText(getApplicationContext(), response.getString("response_message"), Toast.LENGTH_SHORT).show();

                            }



                            listView.setAdapter(cardArrayAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
                return "application/json; charset=utf-8";
            }


        };
        ferryrequests.add(req);

    }


}
