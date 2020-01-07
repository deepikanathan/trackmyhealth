package com.udacity.capstone.trackmyhealth.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.Medication;
import com.udacity.capstone.trackmyhealth.widget.MedicationWidgetService;

public class MainActivity extends AppCompatActivity {

    Medication medication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

            MedicationWidgetService.updateWidget(this, medication);
            Toast.makeText(this, String.format(getString(R.string.added_to_widget_toast), medication.getName()), Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
