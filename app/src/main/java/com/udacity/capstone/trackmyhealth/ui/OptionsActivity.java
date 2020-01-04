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

import com.udacity.capstone.trackmyhealth.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ButterKnife.bind(this);


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        journalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        medicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MedicationsActivity.class);
                startActivity(intent);
            }
        });

        doctorsVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });








//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(OptionsActivity.this, SettingsActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
