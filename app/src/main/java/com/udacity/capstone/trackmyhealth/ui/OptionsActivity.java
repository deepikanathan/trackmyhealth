package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsActivity extends AppCompatActivity {

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        journalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, HealthDataActivity.class);
                startActivity(intent);
            }
        });

        medicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, MedicationsActivity.class);
                startActivity(intent);
            }
        });

        doctorsVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Landing Activity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}

