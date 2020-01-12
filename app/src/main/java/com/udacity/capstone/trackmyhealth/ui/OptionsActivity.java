package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsActivity extends AppCompatActivity {

    private static String TAG = "OptionsActivity";

    @BindView(R.id.profile_button)
    Button profileButton;
    @BindView(R.id.journal_button)
    Button journalButton;
    @BindView(R.id.medications_button)
    Button medicationsButton;
    @BindView(R.id.doctor_visit_button)
    Button doctorsVisitButton;
    @BindView(R.id.settings_button)
    Button settingsButton;

    Tracker mTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, ProfileActivity.class);
                Crashlytics.log(Log.VERBOSE, TAG, "Profile Button pressed");
                startActivity(intent);
            }
        });

        journalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, HealthDataActivity.class);
                Crashlytics.log(Log.VERBOSE, TAG, "Journal Button pressed");
                startActivity(intent);
            }
        });

        medicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, MedicationsActivity.class);
                Crashlytics.log(Log.VERBOSE, TAG, "Medications Button pressed");
                startActivity(intent);
            }
        });

        doctorsVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, ProfileActivity.class);
                Crashlytics.log(Log.VERBOSE, TAG, "Doctors Visit Button pressed");
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, SettingsActivity.class);
                Crashlytics.log(Log.VERBOSE, TAG, "Settings Button pressed");
                startActivity(intent);
            }
        });
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }
}

