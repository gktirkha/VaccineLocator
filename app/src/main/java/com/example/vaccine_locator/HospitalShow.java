package com.example.vaccine_locator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vaccine_locator.Adapters.HospitalAdapter;
import com.example.vaccine_locator.Models.HospitalModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HospitalShow extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ArrayList<HospitalModel> hospitalsArrayList;
    ProgressBar progressBar;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_show);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();
    }

    private void jsonParse() {
        String URL = getIntent().getStringExtra("URL");
        hospitalsArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.shoHospital_rcv);
        HospitalModel blank = new HospitalModel("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", null);
        hospitalsArrayList.add(blank);
        JsonObjectRequest file = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centres = response.getJSONArray("sessions");
                    for (int i = 0; i < centres.length(); i++) {
                        JSONObject data = centres.getJSONObject(i);
                        String id = data.getString("center_id");
                        String hName = data.getString("name");
                        String hAddress = data.getString("address");
                        String feeType = data.getString("fee_type");
                        String vaccineName = data.getString("vaccine");
                        String dose1 = data.getString("available_capacity_dose1");
                        String dose2 = data.getString("available_capacity_dose2");
                        String fee = data.getString("fee");
                        JSONArray slots = data.getJSONArray("slots");
                        ArrayList<String> timings = new ArrayList<>();
                        for (int j = 0; j < slots.length(); j++) {
                            timings.add(slots.getString(j));
                        }
                        if ((Integer.parseInt(dose1) != 0) || Integer.parseInt(dose2) != 0) {
                            HospitalModel hospital = new HospitalModel(id, hName, hAddress, feeType, vaccineName, dose1, dose2, fee, timings);
                            hospitalsArrayList.add(hospital);
                        }
                    }
                    if ((hospitalsArrayList.size() > 1)) {
                        hospitalsArrayList.remove(0);
                    }
                    HospitalAdapter adapter = new HospitalAdapter(HospitalShow.this, hospitalsArrayList);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(HospitalShow.this);
                    recyclerView.setLayoutManager(layoutManager);
                    if (hospitalsArrayList.get(0) == blank) {
                        coordinatorLayout = findViewById(R.id.snackbar);
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Hospitals Found", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent main = new Intent(HospitalShow.this, MainActivity.class);
                                startActivity(main);
                            }
                        });
                        snackbar.show();
                    }
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                coordinatorLayout = findViewById(R.id.snackbar);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Network Error, Please Connect To Internet And Try Again", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent main = new Intent(HospitalShow.this, MainActivity.class);
                        startActivity(main);
                    }
                });
                snackbar.show();
                progressBar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(file);
    }
}