package com.example.vaccine_locator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView counter, pinInput, dateInput;
    RelativeLayout dateInRel;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.main_pin_watcher);
        pinInput = findViewById(R.id.main_pin_textview);
        dateInput = findViewById(R.id.main_date_textview);
        dateInRel = findViewById(R.id.main_date_rel);
        searchButton = findViewById(R.id.main_search_button);
        pinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    counter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!(charSequence.length() == 0)) {
                    counter.setVisibility(View.VISIBLE);
                    counter.setText(MessageFormat.format("{0}/6", charSequence.length()));
                } else counter.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dateInRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog();
            }
        });
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateInput.getText().toString();
                String pin = pinInput.getText().toString();
                if (!date.equals("Enter Date")) {
                    if (pin.length() == 6) {
                        String URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pin + "&date=" + date;
                        Intent intent = new Intent(MainActivity.this, HospitalShow.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("URL", URL);
                        startActivity(intent);
                    } else
                        Toast.makeText(MainActivity.this, "Invalid Pin", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Check Date And Pin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dateDialog() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, i);
                calendar1.set(Calendar.MONTH, i1);
                calendar1.set(Calendar.DATE, i2);
                CharSequence date = DateFormat.format("dd-MM-yyyy", calendar1);
                dateInput.setText(date);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }
}