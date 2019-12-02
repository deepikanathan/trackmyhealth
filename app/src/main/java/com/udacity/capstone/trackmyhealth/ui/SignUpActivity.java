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
import com.udacity.capstone.trackmyhealth.utils.PopulateSpinner;

import java.util.ArrayList;
import java.util.Collections;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        Spinner heightSpinner = findViewById(R.id.height_unit_spinner);
        heightSpinner.setOnItemSelectedListener(this);
        populateHeightUnits(heightSpinner);


        Spinner weightSpinner = findViewById(R.id.weight_unit_spinner);
        weightSpinner.setOnItemSelectedListener(this);
        populateWeightUnits(weightSpinner);

        Spinner stateSpinner = findViewById(R.id.state_sign_up_spinner);
        stateSpinner.setOnItemSelectedListener(this);
        populateState(stateSpinner);

        Spinner countrySpinner = findViewById(R.id.country_sign_up_spinner);
        countrySpinner.setOnItemSelectedListener(this);
        populateCountry(countrySpinner);

    }

    // Creating adapter for height units spinner
    private void populateHeightUnits(Spinner heightSpinner) {

        ArrayAdapter<CharSequence> heightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.height_units_array, android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        heightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        heightSpinner.setAdapter(heightDataAdapter);
    }

    // Creating adapter for weight units spinner
    private void populateWeightUnits(Spinner weightSpinner) {

        ArrayAdapter<CharSequence> weightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.weight_units_array, android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        weightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        weightSpinner.setAdapter(weightDataAdapter);
    }

    private void populateState(Spinner stateSpinner) {

        ArrayList<String> stateList = PopulateSpinner.GetStates();
        Collections.sort(stateList);

        ArrayAdapter<String> stateAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stateList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    private void populateCountry(Spinner countrySpinner) {

        ArrayList<String> countryList = PopulateSpinner.GetCountry();
        Collections.sort(countryList);

        ArrayAdapter<String> countryDataAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList);
        countryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryDataAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
