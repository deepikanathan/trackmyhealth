package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.adapters.MedicationAdapter;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;
import com.udacity.capstone.trackmyhealth.database.AppMedicationDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.Medication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationsActivity extends AppCompatActivity {

    private static String TAG = "MedicationsActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.addFAB)
    FloatingActionButton floatingActionButton;

    private MedicationAdapter mAdapter;
    private AppMedicationDatabase mDb;
    Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medication_list);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        floatingActionButton.setOnClickListener(v -> {
            Crashlytics.log(Log.VERBOSE, TAG, "Edit button pressed");
            startActivity(new Intent(MedicationsActivity.this, MedicationEditActivity.class));
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new MedicationAdapter(this, MedicationsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mDb = AppMedicationDatabase.getInstance(getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                AppExecutors.getInstance().diskIO().execute(() -> {
                    int position = viewHolder.getAdapterPosition();
                    List<Medication> tasks = mAdapter.getTasks();
                    mDb.medicationDao().delete(tasks.get(position));
                    Crashlytics.log(Log.VERBOSE, TAG, "Delete medication in position : " + position);
                });
            }
        }).attachToRecyclerView(mRecyclerView);
        retrieveTasks();

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");
    }

    private void retrieveTasks() {
        mDb.medicationDao().getAllMedications().observe(this, medications -> mAdapter.setTasks(medications));
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
