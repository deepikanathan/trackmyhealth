package com.udacity.capstone.trackmyhealth.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.utils.ImageConverter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    private static String TAG = "ProfileActivity";

    SharedPreferences sharedpreferences;

    @BindView (R.id.profilePic)
    ImageView profilePic;
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
    @BindView(R.id.pcpTitle)
    TextView pcpTitle;
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
    @BindView(R.id.mapItButton)
    ImageButton mapButton;
    @BindView(R.id.editProfile)
    ImageView editProfile;

    Tracker mTracker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        sharedpreferences = getSharedPreferences(Constants.mypreference,
                Context.MODE_PRIVATE);

        //  Profile Picture
        if (sharedpreferences.contains(getResources().getString(R.string.profile_picture))) {
            String imgAsString = sharedpreferences.getString(getResources().getString(R.string.profile_picture), "");
            if (!imgAsString.isEmpty()) {
                Bitmap bmp = ImageConverter.stringToBitMap(imgAsString);
                profilePic.setImageBitmap(bmp);
            }
        }

        editProfile.setOnClickListener(v -> {
            Intent i = new Intent(ProfileActivity.this, SignUpActivity.class);
            Crashlytics.log(Log.VERBOSE, TAG, "Edit Profile button pressed");
           // i.putExtra(Constants.UPDATE_User, sharedpreferences);
            startActivity(i);
        });

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
                gender.setText(getString(R.string.gender_male_sign_up));
            else
                gender.setText(getString(R.string.gender_female_sign_up));
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
            //  PCP City
            if (sharedpreferences.contains(getResources().getString(R.string.pcp_city_sign_up))) {
                pcpCity.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_city_sign_up), ""));
                //  PCP State
                if (sharedpreferences.contains(getResources().getString(R.string.pcp_state_sign_up))) {
                    pcpState.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_state_sign_up), ""));
                    //  PCP Zip
                    if (sharedpreferences.contains(getResources().getString(R.string.pcp_zip_sign_up))) {
                        pcpZip.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_zip_sign_up), ""));
                        //  PCP Phone
                        if (sharedpreferences.contains(getResources().getString(R.string.pcp_phone_sign_up))) {
                            pcpPhone.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_phone_sign_up), ""));
                            pcpTitle.setVisibility(View.VISIBLE);
                            pcpAddress.setVisibility(View.VISIBLE);
                            pcpCity.setVisibility(View.VISIBLE);
                            pcpState.setVisibility(View.VISIBLE);
                            pcpZip.setVisibility(View.VISIBLE);
                            pcpPhone.setVisibility(View.VISIBLE);
                            mapButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }

        mapButton.setOnClickListener(v -> showMap());
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
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

    private void showMap() {

        try {
            StringBuilder sb = new StringBuilder();
            if (!pcpAddress.getText().toString().isEmpty()) {
                sb.append(pcpAddress.getText().toString()).append(", ");
            }
            if (!pcpCity.getText().toString().isEmpty()) {
                sb.append(pcpCity.getText().toString()).append(", ");
            }
            if (!pcpState.getText().toString().isEmpty()) {
                sb.append(pcpState.getText().toString()).append(",");
            }
            if (!pcpZip.getText().toString().isEmpty()) {
                sb.append(pcpZip.getText().toString());
            }
            Uri addressuri = Uri.parse("geo:0,0?q=" + sb.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, addressuri);
            intent.setPackage("com.google.android.apps.maps");
            if (intent.resolveActivity(getPackageManager())!= null) {
                startActivity(intent);
            }
        }
        catch(Exception ex) {
            Crashlytics.logException(new Exception("Exception when showing Map"));
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
