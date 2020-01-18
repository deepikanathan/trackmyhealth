package com.udacity.capstone.trackmyhealth.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.database.AppMedicationDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.Medication;
import com.udacity.capstone.trackmyhealth.utils.FetchMedicationTask;
import com.udacity.capstone.trackmyhealth.utils.NetworkUtils;

import butterknife.BindView;

public class MedicationEditActivity extends AppCompatActivity {

    private static String TAG = "MedicationEditActivity";

    @BindView(R.id.med_name)
    public EditText name;
    @BindView(R.id.med_dose)
    EditText dose;
    @BindView(R.id.med_unit)
    EditText unit;
    @BindView(R.id.med_frequency)
    EditText frequency;
    @BindView(R.id.button)
    Button button;

    int mMedicationId;
    Intent intent;
    private AppMedicationDatabase mDb;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_edit);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        mDb = AppMedicationDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Medication_Id)) {
            button.setText(R.string.update);
            mMedicationId = intent.getIntExtra(Constants.UPDATE_Medication_Id, -1);

            AppExecutors.getInstance().diskIO().execute(() -> {
                Medication medication = mDb.medicationDao().loadMedicationById(mMedicationId);
                Crashlytics.log(Log.VERBOSE, TAG, getString(R.string.edit_med_crash) + medication.getName());
                populateUI(medication);
            });
        }

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        try {
                            if (name.getText().toString().isEmpty() || name.getText().toString().toCharArray().length < 5) {
                                name.setError(getString(R.string.med_name_search));
                            }
                            else {
                                if (NetworkUtils.isNetworkAvailable(MedicationEditActivity.this)) {
                                    Crashlytics.log(Log.VERBOSE, TAG, getString(R.string.search_med_nav_crash) + name.getText().toString());
                                    new FetchMedicationTask(MedicationEditActivity.this, name.getText().toString()).execute();
                                }
                                else {
                                    Toast.makeText(MedicationEditActivity.this, getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        catch (Exception e) {

                            e.printStackTrace();
                        }
                        return true;
                    }
                }
                return false;
            }

        });

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        name = findViewById(R.id.med_name);
        dose = findViewById(R.id.med_dose);
        unit = findViewById(R.id.med_unit);
        frequency = findViewById(R.id.med_frequency);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> onSaveButtonClicked());
    }

    private boolean checkDataEntered() {
        boolean allClear = true;

        if (isEmpty(name)) {
            allClear = false;
            name.requestFocus();
            name.setError(getString(R.string.med_name_required));
        }
        if (isEmpty(dose)) {
            allClear = false;
            dose.requestFocus();
            dose.setError(getString(R.string.med_dose_required));
        }
        if (isEmpty(unit)) {
            allClear = false;
            unit.requestFocus();
            unit.setError(getString(R.string.med_unit_required));
        }
        if (isEmpty(frequency)) {
            allClear = false;
            frequency.requestFocus();
            frequency.setError(getString(R.string.med_freq_required));
        }

        return allClear;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void onSaveButtonClicked() {

        if (checkDataEntered()) {
            final Medication medication = new Medication(
                    name.getText().toString(),
                    dose.getText().toString(),
                    unit.getText().toString(),
                    frequency.getText().toString());

            AppExecutors.getInstance().diskIO().execute(() -> {
                if (!intent.hasExtra(Constants.UPDATE_Medication_Id)) {
                    mDb.medicationDao().insert(medication);
                } else {
                    medication.setId(mMedicationId);
                    mDb.medicationDao().update(medication);
                }
                Crashlytics.log(Log.VERBOSE, TAG, getString(R.string.save_med_crash) + medication.getName());
                finish();
            });
        }
    }

    private void populateUI(Medication medication) {
        if (medication == null) {
            return;
        }
        name.setText(medication.getName());
        dose.setText(medication.getDose());
        unit.setText(medication.getUnit());
        frequency.setText(medication.getFrequency());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
