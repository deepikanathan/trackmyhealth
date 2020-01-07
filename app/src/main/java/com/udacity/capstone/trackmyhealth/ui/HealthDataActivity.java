package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.adapters.HealthDataAdapter;
import com.udacity.capstone.trackmyhealth.database.AppHealthDataDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.HealthData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthDataActivity extends AppCompatActivity {

    @BindView(R.id.healthDataRecyclerView)
    RecyclerView healthDataRecyclerView;
    @BindView(R.id.addHealthDataFAB)
    FloatingActionButton addHealthDataFAB;

    private HealthDataAdapter mAdapter;
    private AppHealthDataDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_health_data_list);
        ButterKnife.bind(this);

        addHealthDataFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthDataActivity.this, HealthDataEditActivity.class));
            }
        });

        healthDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new HealthDataAdapter(this, HealthDataActivity.this);
        healthDataRecyclerView.setAdapter(mAdapter);
        mDb = AppHealthDataDatabase.getInstance(getApplicationContext());

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
                        List<HealthData> tasks = mAdapter.getTasks();
                        mDb.healthDataDao().delete(tasks.get(position));

                    }
                });
            }
        }).attachToRecyclerView(healthDataRecyclerView);
        retrieveTasks();
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        retrieveTasks();
//    }

    private void retrieveTasks() {
        mDb.healthDataDao().getAllHealthData().observe(this, new Observer<List<HealthData>>() {
            @Override
            public void onChanged(@Nullable List<HealthData> healthData) {
                mAdapter.setTasks(healthData);
            }
        });
    }

    }
