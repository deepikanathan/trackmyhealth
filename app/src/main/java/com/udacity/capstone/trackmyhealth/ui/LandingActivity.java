package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;

import butterknife.ButterKnife;


public class LandingActivity extends AppCompatActivity {

    private static String TAG = "LandingActivity";
    Tracker mTracker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InvokeSignUpActivity();
            }
        });
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        mTracker.setScreenName("Landing Activity");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
//    }

    private void InvokeSignUpActivity() {
        Crashlytics.log(Log.VERBOSE, TAG, "SignUp clicked");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}
