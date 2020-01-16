package com.udacity.capstone.trackmyhealth.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.analytics.Tracker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.adapters.HealthDataAdapter;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;
import com.udacity.capstone.trackmyhealth.database.AppHealthDataDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.HealthData;

import com.crashlytics.android.Crashlytics;
import com.udacity.capstone.trackmyhealth.widget.HealthDataWidgetProvider;
import com.udacity.capstone.trackmyhealth.widget.HealthDataWidgetService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthDataActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "HealthDataActivity";

    @BindView(R.id.healthDataRecyclerView)
    RecyclerView healthDataRecyclerView;
    @BindView(R.id.addHealthDataFAB)
    FloatingActionButton addHealthDataFAB;

    private HealthDataAdapter mAdapter;
    private AppHealthDataDatabase mDb;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_health_data_list);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();


        addHealthDataFAB.setOnClickListener(v -> {
            Crashlytics.log(Log.VERBOSE, TAG, "Add Health Data Button pressed");
            startActivity(new Intent(HealthDataActivity.this, HealthDataEditActivity.class));
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

                AppExecutors.getInstance().diskIO().execute(() -> {
                    try{
                        int position = viewHolder.getAdapterPosition();
                        List<HealthData> tasks = mAdapter.getTasks();
                        mDb.healthDataDao().delete(tasks.get(position));

                        Crashlytics.log(Log.VERBOSE, TAG, "Delete Health Data");
                        Crashlytics.setInt("Health Data Position", position);
                    }
                    catch (Exception ex) {
                        Crashlytics.logException(new Exception(TAG + " : Exception when deleting Health Data"));
                    }
                });
            }
        }).attachToRecyclerView(healthDataRecyclerView);
        retrieveTasks();

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void retrieveTasks() {
        mDb.healthDataDao().getAllHealthData().observe(this, healthData -> mAdapter.setTasks(healthData));
    }

    @Override
    public void onClick(View v) {
       // Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_to_widget) {

            List<HealthData> tasks = mAdapter.getTasks();
            if (tasks != null && tasks.size() > 0) {
                HealthData healthData = tasks.get(0);

                com.udacity.capstone.trackmyhealth.models.HealthData model = new com.udacity.capstone.trackmyhealth.models.HealthData(healthData.getA1c(), healthData.getBloodsugar(), healthData.getDateofvisit(), healthData.getDocname(),healthData.getHdl(),healthData.getLdl(),healthData.getTriglycerides(),healthData.getWeight());

                Crashlytics.log(Log.VERBOSE, TAG, "Add To Widget HEALTHDATA : " + healthData.getDateofvisit());

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, HealthDataWidgetProvider.class));

                HealthDataWidgetService.updateWidget(this, model);
                return true;
            }
            else {
                Toast.makeText(this, "Did not find any Health Data to add to widget", Toast.LENGTH_LONG).show();
                Crashlytics.log(Log.VERBOSE, TAG, "Did not find any Health Data to add to widget");
                return super.onOptionsItemSelected(item);
            }
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

}
