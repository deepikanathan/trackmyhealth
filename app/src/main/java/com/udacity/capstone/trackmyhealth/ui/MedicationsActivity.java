package com.udacity.capstone.trackmyhealth.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.adapters.MedicationAdapter;
import com.udacity.capstone.trackmyhealth.database.AppDatabase;

import butterknife.BindView;

public class MedicationsActivity extends AppCompatActivity {

    @BindView(R.id.medication_name)
    TextView medication_name;
    @BindView(R.id.dose)
    TextView medication_dose;
    @BindView(R.id.unit)
    TextView medication_unit;
    @BindView(R.id.frequency)
    TextView medication_frequency;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.addFAB)
    FloatingActionButton floatingActionButton;

    private MedicationAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
