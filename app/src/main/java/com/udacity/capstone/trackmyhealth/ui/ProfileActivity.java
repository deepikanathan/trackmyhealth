package com.udacity.capstone.trackmyhealth.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.udacity.capstone.trackmyhealth.R;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String mypreference = "User";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        setFirstName();

        TextView email = findViewById(R.id.emailValue);
        if (sharedpreferences.contains(getResources().getString(R.string.email_sign_up))) {
            email.setText(sharedpreferences.getString(getResources().getString(R.string.email_sign_up), ""));
        }

        TextView dob = findViewById(R.id.dobValue);
        if (sharedpreferences.contains(getResources().getString(R.string.dob_sign_up))) {
            dob.setText(sharedpreferences.getString(getResources().getString(R.string.dob_sign_up), ""));
        }

        TextView gender = findViewById(R.id.gender_value);
        if (sharedpreferences.contains(getResources().getString(R.string.gender))) {
            boolean isMale = sharedpreferences.getBoolean(getResources().getString(R.string.gender), false);
            if (isMale)
                gender.setText("Male");
            else
                gender.setText("Female");

        }
    }

    private void setFirstName() {

        TextView name = findViewById(R.id.nameValue);
        String firstName = null;
        String lastName = null;
        if (sharedpreferences.contains(getResources().getString(R.string.first_name_sign_up))) {
            firstName = sharedpreferences.getString(getResources().getString(R.string.first_name_sign_up), "");
        }
        if (sharedpreferences.contains(getResources().getString(R.string.last_name_sign_up))) {
            lastName = sharedpreferences.getString(getResources().getString(R.string.last_name_sign_up), "");
        }
        name.setText(firstName + " " + lastName);
    }
}
