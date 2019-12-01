package com.udacity.capstone.trackmyhealth.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.udacity.capstone.trackmyhealth.R;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        // Spinner element
        Spinner heightSpinner = (Spinner) findViewById(R.id.height_unit_spinner);

        // Spinner click listener
        heightSpinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner

        ArrayAdapter<CharSequence> heightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.height_units_array, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        heightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        heightSpinner.setAdapter(heightDataAdapter);


        // Spinner element
        Spinner weightSpinner = (Spinner) findViewById(R.id.weight_unit_spinner);

        // Spinner click listener
        weightSpinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner

        ArrayAdapter<CharSequence> weightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.weight_units_array, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        weightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        weightSpinner.setAdapter(weightDataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
