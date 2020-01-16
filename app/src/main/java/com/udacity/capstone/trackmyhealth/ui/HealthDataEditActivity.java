package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.database.AppHealthDataDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.HealthData;

public class HealthDataEditActivity extends AppCompatActivity {

    private static String TAG = "HealthDataEditActivity";
    EditText docName, visitDate, a1c, bloodsugar, triglycerides, weight, ldl, hdl;
    Button button;
    int mHealthDataId;
    Intent intent;
    private AppHealthDataDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_health_data_edit);
        initViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        mDb = AppHealthDataDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Health_Data_Id)) {
            button.setText(R.string.update);
            mHealthDataId = intent.getIntExtra(Constants.UPDATE_Health_Data_Id, -1);

            AppExecutors.getInstance().diskIO().execute(() -> {
                HealthData healthData = mDb.healthDataDao().loadHealthDataById(mHealthDataId);
                populateUI(healthData);
                Crashlytics.log(Log.VERBOSE, TAG, "HealthDataEditActivity PopulateUI");
            });
        }
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        docName = findViewById(R.id.doc_name);
        visitDate = findViewById(R.id.date_of_visit);
        a1c = findViewById(R.id.a1c_widget);
        bloodsugar = findViewById(R.id.blood_sugar);
        triglycerides = findViewById(R.id.triglycerides);
        weight = findViewById(R.id.weight);
        ldl = findViewById(R.id.ldl);
        hdl = findViewById(R.id.hdl);

        button = findViewById(R.id.add_health_data);
        button.setOnClickListener(v -> onSaveButtonClicked());
    }

    private void populateUI(HealthData healthData) {
        if (healthData == null) {
            return;
        }
        docName.setText(healthData.getDocname());
        visitDate.setText(healthData.getDateofvisit());
        a1c.setText(healthData.getA1c());
        bloodsugar.setText(healthData.getBloodsugar());
        triglycerides.setText(healthData.getTriglycerides());
        weight.setText(healthData.getWeight());
        ldl.setText(healthData.getLdl());
        hdl.setText(healthData.getHdl());
    }

    private boolean checkDataEntered() {
        boolean allClear = true;

        if (isEmpty(docName)) {
            allClear = false;
            docName.requestFocus();
            docName.setError("Physician Name is required");
        }
        if (isEmpty(visitDate)) {
            allClear = false;
            visitDate.requestFocus();
            visitDate.setError("Physician Name is required");
        }
        if (isEmpty(a1c)) {
            allClear = false;
            a1c.requestFocus();
            a1c.setError("A1C is required");
        }
        if (isEmpty(bloodsugar)) {
            allClear = false;
            bloodsugar.requestFocus();
            bloodsugar.setError("Blood Sugar is required");
        }
        if (isEmpty(triglycerides)) {
            allClear = false;
            triglycerides.requestFocus();
            triglycerides.setError("Triglycerides is required");
        }
        if (isEmpty(weight)) {
            allClear = false;
            weight.requestFocus();
            weight.setError("Weight is required");
        }
        if (isEmpty(ldl)) {
            allClear = false;
            ldl.requestFocus();
            ldl.setError("LDL is required");
        }
        if (isEmpty(hdl)) {
            allClear = false;
            hdl.requestFocus();
            hdl.setError("HDL is required");
        }

        return allClear;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void onSaveButtonClicked() {

        try {
            if (checkDataEntered()) {
                Crashlytics.log(Log.VERBOSE, TAG, "HealthDataEditActivity Save button clicked ");
                Crashlytics.setString("Doc Name", docName.getText().toString());
                Crashlytics.setString("Visit Date", visitDate.getText().toString());
                Crashlytics.setString("A1c", a1c.getText().toString());
                Crashlytics.setString("BloodSugar", bloodsugar.getText().toString());
                Crashlytics.setString("Triglycerides", triglycerides.getText().toString());
                Crashlytics.setString("Weight", weight.getText().toString());
                Crashlytics.setString("LDL", ldl.getText().toString());
                Crashlytics.setString("HDL", hdl.getText().toString());
                final HealthData data = new HealthData(
                        a1c.getText().toString(),
                        bloodsugar.getText().toString(),
                        visitDate.getText().toString(),
                        docName.getText().toString(),
                        hdl.getText().toString(),
                        ldl.getText().toString(),
                        triglycerides.getText().toString(),
                        weight.getText().toString());

                AppExecutors.getInstance().diskIO().execute(() -> {
                    if (!intent.hasExtra(Constants.UPDATE_Health_Data_Id)) {
                        mDb.healthDataDao().insert(data);
                    } else {
                        data.setId(mHealthDataId);
                        mDb.healthDataDao().update(data);
                    }
                    finish();
                });
            }
        }
        catch (Exception ex) {
            Crashlytics.logException(new Exception(TAG + " : Exception when saving Health Data"));
        }
    }
}
