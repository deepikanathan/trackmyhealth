package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.database.AppDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.Medication;

public class MedicationEditActivity extends AppCompatActivity {

    EditText name, dose, unit, frequncy;
    Button button;
    int mMedicationId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initViews();

        mDb = AppDatabase.getDatabase(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Medication_Id)) {
            button.setText("Update");
            mMedicationId = intent.getIntExtra(Constants.UPDATE_Medication_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Medication person = mDb.medicationDao().loadMedicationById(mMedicationId);
                    populateUI(person);
                }
            });
        }
    }

    private void initViews() {
        name = findViewById(R.id.med_name);
        dose = findViewById(R.id.med_dose);
        unit = findViewById(R.id.med_unit);
        frequncy = findViewById(R.id.med_frequency);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Medication medi = new Medication(
                name.getText().toString(),
                dose.getText().toString(),
                unit.getText().toString(),
                frequncy.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Medication_Id)) {
                    mDb.medicationDao().insert(medi);
                } else {
                    medi.setId(mMedicationId);
                    mDb.medicationDao().updatePerson(medi);
                }
                finish();
            }
        });
    }

    private void populateUI(Medication person) {
        if (person == null) {
            return;
        }

        name.setText(person.getName());
        dose.setText(person.getDose());
        unit.setText(person.getUnit());
        frequncy.setText(person.getFrequency());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
