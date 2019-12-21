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
        setContentView(R.layout.activity_userprofile);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        //  name
        setName();

        //  email
        TextView email = findViewById(R.id.emailValue);
        if (sharedpreferences.contains(getResources().getString(R.string.email_sign_up))) {
            email.setText(sharedpreferences.getString(getResources().getString(R.string.email_sign_up), ""));
        }

        //  DOB
        TextView dob = findViewById(R.id.dobValue);
        if (sharedpreferences.contains(getResources().getString(R.string.dob_sign_up))) {
            dob.setText(sharedpreferences.getString(getResources().getString(R.string.dob_sign_up), ""));
        }

        //  gender
        TextView gender = findViewById(R.id.genderValue);
        if (sharedpreferences.contains(getResources().getString(R.string.gender))) {
            boolean isMale = sharedpreferences.getBoolean(getResources().getString(R.string.gender), false);
            if (isMale)
                gender.setText("Male");
            else
                gender.setText("Female");

        }

        //  ICE
        TextView icename = findViewById(R.id.iceNameValue);
        if (sharedpreferences.contains(getResources().getString(R.string.ice_name_sign_up))) {
            icename.setText(sharedpreferences.getString(getResources().getString(R.string.ice_name_sign_up), ""));
        }

        //  ICE Phone
        TextView icephone = findViewById(R.id.icePhoneValue);
        if (sharedpreferences.contains(getResources().getString(R.string.ice_phone_number_sign_up))) {
            icephone.setText(sharedpreferences.getString(getResources().getString(R.string.ice_phone_number_sign_up), ""));
        }

        //  height
        TextView height = findViewById(R.id.heightValue);
        if (sharedpreferences.contains(getResources().getString(R.string.height_sign_up))) {
            height.setText(sharedpreferences.getString(getResources().getString(R.string.height_sign_up), ""));
        }

        //  weight
        TextView weight = findViewById(R.id.weightValue);
        if (sharedpreferences.contains(getResources().getString(R.string.weight_sign_up))) {
            weight.setText(sharedpreferences.getString(getResources().getString(R.string.weight_sign_up), ""));
        }

        //  PCP Name
        TextView pcpName = findViewById(R.id.pcpName);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_name_sign_up))) {
            pcpName.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_name_sign_up), ""));
        }

        //  PCP Address
        TextView pcpAddress = findViewById(R.id.pcpAddress1);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_address_sign_up))) {
            pcpAddress.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_address_sign_up), ""));
        }

        //  PCP City
        TextView pcpCity = findViewById(R.id.pcpCity);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_city_sign_up))) {
            pcpCity.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_city_sign_up), ""));
        }

        //  PCP State
        TextView pcpState = findViewById(R.id.pcpState);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_state_sign_up))) {
            pcpState.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_state_sign_up), ""));
        }

        //  PCP Zip
        TextView pcpZip = findViewById(R.id.pcpZip);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_zip_sign_up))) {
            pcpZip.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_zip_sign_up), ""));
        }

        //  PCP Phone
        TextView pcpPhone = findViewById(R.id.pcpPhone);
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_phone_sign_up))) {
            pcpPhone.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_phone_sign_up), ""));
        }

    }

    private void setName() {

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
