package com.udacity.capstone.trackmyhealth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.Medication;

public class MainActivity extends AppCompatActivity {

    Medication medication;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }
}
