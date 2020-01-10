package com.udacity.capstone.trackmyhealth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.Medication;
import com.udacity.capstone.trackmyhealth.widget.MedicationWidgetService;

public class MainActivity extends AppCompatActivity {

    Medication medication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}


}
