package com.example.mbitaferrydev;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.mbitaferrydev.Database.DatabaseHelper;
import com.example.mbitaferrydev.Database.ReffNumber;
import com.example.mbitaferrydev.Database.TicketCount;
import com.example.mbitaferrydev.Database.TicketsSQLiteDatabaseHandler;
import com.example.mbitaferrydev.Models.Routes;
import com.example.mbitaferrydev.customApplicationClass.CustomAppClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import spencerstudios.com.fab_toast.FabToast;

public class HomeInitialActivity extends AppCompatActivity {

    private static final String TAG = "CardListActivity";
    final Context context = this;
    EditText username, password;
    Dialog dialog;
    ProgressDialog progressDialog;
    String seats;
    CustomAppClass app;
    private FerryRouteCardArrayAdapter cardArrayAdapter;
    private ListView listView;
    private DatabaseHelper db;
    private TicketsSQLiteDatabaseHandler ticketsdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_initial);
//        mainListView = findViewById(R.id.ferryRoutesId);
//        ferryList = new ArrayList<String>();
//        mainListView.setHeaderView(R.layout.header);

        ticketsdb = new TicketsSQLiteDatabaseHandler(this);


        app = (CustomAppClass) getApplication();
        app.setApi_key("c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7");
        app.setUsername("emuswailit");
        app.setHash_key("1FBEAD9B-D9CD-400D-ADF3-F4D0E639CEE0");

        progressDialog = new ProgressDialog(HomeInitialActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("Login In");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
//                        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);


        listView =  findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new FerryRouteCardArrayAdapter(getApplicationContext(), R.layout.list_item_card);


        loadFerryRoutes();

        db = new DatabaseHelper(this);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selected = String.valueOf(listView.indexOfChild(view));

                String Mbita = "Mbita";
                String mfangano = "Mfangano";

                String luanda_kotiono = "Luanda Kotieno";


                if (selected.equals("1")) {
                    app.setTo(mfangano);
                    app.setFrom(Mbita);
                } else if (selected.equals("2")) {
                    app.setTo(Mbita);
                    app.setFrom(mfangano);

                } else if (selected.equals("3")) {
                    app.setTo(luanda_kotiono);
                    app.setFrom(Mbita);

                } else if (selected.equals("4")) {
                    app.setTo(Mbita);
                    app.setFrom(luanda_kotiono);

                }


                Toast.makeText(getApplicationContext(), app.getFrom() + "  " + app.getTo(), Toast.LENGTH_SHORT).show();


                dialog = new Dialog(context);
                dialog.setContentView(R.layout.login_dialog);
                dialog.setTitle("      Account Login");


                username = dialog.findViewById(R.id.editTextusername);
                password = dialog.findViewById(R.id.editTextpassword);

                Button dialogButton = (Button) dialog.findViewById(R.id.btnlogin);


                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(10000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        }).start();
                    }


                });
                dialog.show();


            }
        });

    }

    private void login() {

        final String email = username.getText().toString();
        final String pass = password.getText().toString();


        //validating inputs
        if (TextUtils.isEmpty(email)) {
            username.setError("Please enter your email address or phone");
            username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        } else {

            progressDialog.show();
            RequestQueue reserverequestQueue = Volley.newRequestQueue(HomeInitialActivity.this);
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("username", "rWIv7GWzSp");
            params.put("api_key", "831b238c5cd73308520e38bbc6c1774f470a89e96d07a5bb6bcac3b86456f889");
            params.put("action", "UserLogin");
            params.put("clerk_username", email);
            params.put("clerk_password", pass);


            JsonObjectRequest req = new JsonObjectRequest(ApiUrls.apiUrl, new JSONObject(params),
                    response -> {
                        try {

                            if (response.getInt("response_code") == 0) {
                                String first_name = response.getString("first_name");
                                String last_name = response.getString("last_name");


                                Log.d("log in ", first_name);
                                progressDialog.dismiss();


                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));


                            } else {
                                FabToast.makeText(getApplicationContext(), response.getString("response_message"), FabToast.LENGTH_SHORT,FabToast.WARNING,  FabToast.POSITION_DEFAULT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }


            };
//            reserverequestQueue.getCache().clear();

            reserverequestQueue.add(req);

        }
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

                                    String name = ferry_route.substring(8);


                                    Log.d("Ferry Routes: ", ferry_route);

                                    Routes card = new Routes(name);

                                    cardArrayAdapter.add(card);

                                    loadTickets();


                                }


                            } else {

                                FabToast.makeText(getApplicationContext(), response.getString("response_message"), FabToast.LENGTH_SHORT,FabToast.WARNING,FabToast.POSITION_DEFAULT).show();

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


    private void loadTickets() {


        RequestQueue requestQueue = Volley.newRequestQueue(HomeInitialActivity.this);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", "emuswailit");
        params.put("api_key", "c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7");
        params.put("action", "SearchSchedule");
        params.put("travel_from", "3");
        params.put("travel_to", "1");
        params.put("travel_date", "27-10-2018");
        params.put("hash", "1FBEAD9B-D9CD-400D-ADF3-F4D0E639CEE0");

        JsonObjectRequest req = new JsonObjectRequest(ApiUrls.apiUrl, new JSONObject(params),
                response -> {
                    try {

                        if (response.getInt("response_code") == 0) {

                            loadReffNumbers();

                            JSONArray jsonArray = response.getJSONArray("bus");
                            JSONArray jsonArrayPrice = response.getJSONObject("bus").getJSONArray("price");


                            String myString = response.getJSONObject("bus").getJSONObject("price").getString("name");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                seats = jsonObject1.getString("total_seats");


                                Log.d("Count", String.valueOf(seats));

                                // Inserting Contacts
                                Log.d("Insert: ", "Inserting ..");


                            }


                            Log.d("Price Data: ", myString.toString());

                            try {
                                FileWriter file = new FileWriter(Environment.getExternalStorageState());
                                file.write(String.valueOf(jsonArrayPrice));
                                file.flush();
                                file.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            db.addCount(new TicketCount(seats));


                        } else {
                            FabToast.makeText(getApplicationContext(), response.getString("response_message"), FabToast.LENGTH_SHORT).show();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
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

        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=utf-8";
            }


        };

        requestQueue.add(req);


    }

    private void loadReffNumbers() {


        RequestQueue requestQueue = Volley.newRequestQueue(HomeInitialActivity.this);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("developer_username", "emuswailit");
        params.put("developer_api_key", "c8e254c0adbe4b2623ff85567027d78d4cc066357627e284d4b4a01b159d97a7");
        params.put("action", "batchgeneratereferencenumbers");
        params.put("limit", "5");


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, ApiUrls.refGeneration, new JSONObject(params),
                response -> {
                    try {

                        if (response.getInt("response_code") == 0) {
                            JSONArray jsonArray = response.getJSONArray("reference_numbers");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String refs = jsonObject1.getString("name");
                                String id = jsonObject1.getString("id");

                                Log.d("Refs", String.valueOf(refs));

                                // Inserting Contacts
                                ticketsdb.createRef(new ReffNumber(Integer.valueOf(id), refs));


                            }


                        } else {
                            Toast.makeText(getApplicationContext(), response.getString("response_message"), Toast.LENGTH_SHORT).show();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
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

        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=utf-8";
            }


        };

        requestQueue.add(req);


    }



}
