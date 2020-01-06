package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.adapters.MedicationAdapter;
import com.udacity.capstone.trackmyhealth.database.AppDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.Medication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsActivity extends AppCompatActivity {

//    @BindView(R.id.medication_name)
//    TextView medication_name;
//    @BindView(R.id.dose)
//    TextView medication_dose;
//    @BindView(R.id.unit)
//    TextView medication_unit;
//    @BindView(R.id.frequency)
//    TextView medication_frequency;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.addFAB)
    FloatingActionButton floatingActionButton;

    private MedicationAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medication_list);
        ButterKnife.bind(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicationsActivity.this, MedicationEditActivity.class));
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new MedicationAdapter(this, MedicationsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mDb = AppDatabase.getInstance(getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Medication> tasks = mAdapter.getTasks();
                        mDb.medicationDao().delete(tasks.get(position));

                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);
        retrieveTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {

        mDb.medicationDao().getAllMedications().observe(this, new Observer<List<Medication>>() {
            @Override
            public void onChanged(@Nullable List<Medication> medications) {
                mAdapter.setTasks(medications);
            }
        });
    }
}
