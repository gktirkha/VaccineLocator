package com.example.vaccine_locator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccine_locator.Models.HospitalModel;

import java.util.ArrayList;

public class HospitalDetails extends AppCompatActivity {
    HospitalModel hospital;
    TextView hName,hID,feeType,vaccineName,vaccinePrice,address,dose1,dose2;
    ListView timings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);
        hospital = (HospitalModel) getIntent().getSerializableExtra("obj");

        hName = findViewById(R.id.hospitalDetail_hName);
        hID = findViewById(R.id.hospitalDetail_id);
        feeType = findViewById(R.id.hospitalDetail_feeType);
        vaccineName = findViewById(R.id.hospitalDetail_vaccineName);
        vaccinePrice = findViewById(R.id.hospitalDetail_vaccinePrice);
        address = findViewById(R.id.hospitalDetail_address);
        dose1 = findViewById(R.id.hospitalDetail_dose1);
        dose2 = findViewById(R.id.hospitalDetail_dose2);
        timings = findViewById(R.id.hospitalDetail_timings);

        hName.setText(hospital.gethName());
        hID.setText("Hospital ID: "+hospital.getId());
        feeType.setText("Fee Type: "+hospital.getFeeType());
        vaccineName.setText("Vaccine Name: "+hospital.getVaccineName());
        vaccinePrice.setText("Vaccination Fee: "+hospital.getFee());
        address.setText(hospital.gethAddress());
        dose1.setText("Dose1 Avalable: "+hospital.getDose1());
        dose2.setText("Dose2 Avalable: "+hospital.getDose2());
       if (hospital.getTimings()!=null){
           ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,hospital.getTimings());
           timings.setAdapter(adapter);
       }else timings.setVisibility(View.GONE);

    }
}