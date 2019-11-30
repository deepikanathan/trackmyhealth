package com.udacity.capstone.trackmyhealth.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.utils.CustomOnItemSelectedListener;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

//        Spinner weight_spinner = (Spinner) findViewById(R.id.weight_unit_spinner);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> weightadapter = ArrayAdapter.createFromResource(this,
//                R.array.weight_units_array, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        weightadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        weight_spinner.setAdapter(weightadapter);
//        weight_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//
//
//
//
//
//        Spinner height_spinner = (Spinner) findViewById(R.id.height_unit_spinner);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> heightadapter = ArrayAdapter.createFromResource(this,
//                R.array.weight_units_array, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        heightadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        height_spinner.setAdapter(heightadapter);
//        height_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
