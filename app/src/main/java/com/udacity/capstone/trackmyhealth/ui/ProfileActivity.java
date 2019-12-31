package com.udacity.capstone.trackmyhealth.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.udacity.capstone.trackmyhealth.R;

import butterknife.BindView;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String mypreference = "User";


    @BindView(R.id.emailValue)
    TextView email;
    @BindView(R.id.dobValue)
    TextView dob;
    @BindView(R.id.genderValue)
    TextView gender;
    @BindView(R.id.iceNameValue)
    TextView icename;
    @BindView(R.id.icePhoneValue)
    TextView icephone;
    @BindView(R.id.heightValue)
    TextView height;
    @BindView(R.id.weightValue)
    TextView weight;
    @BindView(R.id.pcpName)
    TextView pcpName;
    @BindView(R.id.pcpAddress1)
    TextView pcpAddress;
    @BindView(R.id.pcpCity)
    TextView pcpCity;
    @BindView(R.id.pcpState)
    TextView pcpState;
    @BindView(R.id.pcpZip)
    TextView pcpZip;
    @BindView(R.id.pcpPhone)
    TextView pcpPhone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        //  name
        setName();

        //  email
        if (sharedpreferences.contains(getResources().getString(R.string.email_sign_up))) {
            email.setText(sharedpreferences.getString(getResources().getString(R.string.email_sign_up), ""));
        }

        //  DOB
        if (sharedpreferences.contains(getResources().getString(R.string.dob_sign_up))) {
            dob.setText(sharedpreferences.getString(getResources().getString(R.string.dob_sign_up), ""));
        }

        //  gender
        if (sharedpreferences.contains(getResources().getString(R.string.gender))) {
            boolean isMale = sharedpreferences.getBoolean(getResources().getString(R.string.gender), false);
            if (isMale)
                gender.setText("Male");
            else
                gender.setText("Female");

        }

        //  ICE
        if (sharedpreferences.contains(getResources().getString(R.string.ice_name_sign_up))) {
            icename.setText(sharedpreferences.getString(getResources().getString(R.string.ice_name_sign_up), ""));
        }

        //  ICE Phone
        if (sharedpreferences.contains(getResources().getString(R.string.ice_phone_number_sign_up))) {
            icephone.setText(sharedpreferences.getString(getResources().getString(R.string.ice_phone_number_sign_up), ""));
        }

        //  height
        if (sharedpreferences.contains(getResources().getString(R.string.height_sign_up))) {
            height.setText(sharedpreferences.getString(getResources().getString(R.string.height_sign_up), ""));
        }

        //  weight
        if (sharedpreferences.contains(getResources().getString(R.string.weight_sign_up))) {
            weight.setText(sharedpreferences.getString(getResources().getString(R.string.weight_sign_up), ""));
        }

        //  PCP Name
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_name_sign_up))) {
            pcpName.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_name_sign_up), ""));
        }

        //  PCP Address
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_address_sign_up))) {
            pcpAddress.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_address_sign_up), ""));
        }

        //  PCP City
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_city_sign_up))) {
            pcpCity.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_city_sign_up), ""));
        }

        //  PCP State
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_state_sign_up))) {
            pcpState.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_state_sign_up), ""));
        }

        //  PCP Zip
        if (sharedpreferences.contains(getResources().getString(R.string.pcp_zip_sign_up))) {
            pcpZip.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_zip_sign_up), ""));
        }

        //  PCP Phone
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
