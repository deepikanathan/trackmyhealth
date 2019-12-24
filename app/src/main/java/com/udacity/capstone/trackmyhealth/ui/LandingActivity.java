package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.udacity.capstone.trackmyhealth.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);



        //  add toolbar
  //      Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        Button signUpButton = findViewById(R.id.sign_up_button);
//
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                InvokeSignUpActivity();
//            }
//        });

    }

//    private void setActionBarTitle() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setTitle("Track Your Health");
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
//
//    private void InvokeSignUpActivity()
//    {
//        Intent intent = new Intent(this, SignUpActivity.class);
//        startActivity(intent);
//    }

}
