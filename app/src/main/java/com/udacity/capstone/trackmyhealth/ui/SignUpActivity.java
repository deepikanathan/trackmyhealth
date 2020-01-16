package com.udacity.capstone.trackmyhealth.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.utils.ImageConverter;
import com.udacity.capstone.trackmyhealth.utils.PopulateSpinner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static String TAG = "SignUpActivity";

    String state;

    @BindView (R.id.profileImageView)
    ImageView profileImageView;
    @BindView (R.id.firstNameEditText)
    EditText firstNameEditText;
    @BindView (R.id.lastNameTextView)
    EditText lastNameTextView;
    @BindView (R.id.dobEditText)
    EditText dobEditText;
    @BindView (R.id.emailEditText)
    EditText emailEditText;
    @BindView (R.id.iceNameEditText)
    EditText iceNameEditText;
    @BindView (R.id.icePhoneEditText)
    EditText icePhoneEditText;
    @BindView (R.id.heightEditText)
    EditText heightEditText;
    @BindView (R.id.weightEditText)
    EditText weightEditText;
    @BindView (R.id.pcpNameEditText)
    EditText pcpNameEditText;
    @BindView (R.id.pcpAddressEditText)
    EditText pcpAddressEditText;
    @BindView (R.id.pcpCityEditText)
    EditText pcpCityEditText;
    @BindView(R.id.state_sign_up_spinner)
    Spinner stateSpinner;
    @BindView (R.id.pcpZipEditText)
    EditText pcpZipEditText;
    @BindView (R.id.pcpPhoneEditText)
    EditText pcpPhoneEditText;

    @BindView(R.id.addAccount)
    Button signUpButton;
    Tracker mTracker;

    private static int RESULT_LOAD_IMAGE = 1;
    private int mYear, mMonth, mDay;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        signUpButton.setOnClickListener(v -> {
            createAccount();
            Crashlytics.log(Log.VERBOSE, TAG, "SignUp button pressed");
        });

        dobEditText.setOnClickListener(this);
        dobEditText.setShowSoftInputOnFocus(false);


        Picasso.get()
                .load(R.drawable.user_48x48)
                .placeholder(R.drawable.user_48x48)
                .into(profileImageView);

        stateSpinner.setOnItemSelectedListener(this);
        populateState(stateSpinner);

        Button buttonLoadImage = findViewById(R.id.importProfilePicture);
        buttonLoadImage.setOnClickListener(arg0 -> {

            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        });

        loadValues();

        Crashlytics.log(Log.VERBOSE, TAG, "onCreate finished");
    }

    private void loadValues() {

        try {
            sharedpreferences = getSharedPreferences(Constants.mypreference, Context.MODE_PRIVATE);

            //  Profile Picture
            if (sharedpreferences.contains(getResources().getString(R.string.profile_picture))) {
                String imgAsString = sharedpreferences.getString(getResources().getString(R.string.profile_picture), "");
                if (!imgAsString.isEmpty()) {
                    Bitmap bmp = ImageConverter.stringToBitMap(imgAsString);
                    profileImageView.setImageBitmap(bmp);
                }
            }
            if (sharedpreferences.contains(getResources().getString(R.string.first_name_sign_up))) {
                firstNameEditText.setText(sharedpreferences.getString(getResources().getString(R.string.first_name_sign_up), ""));
            }
            if (sharedpreferences.contains(getResources().getString(R.string.last_name_sign_up))) {
                lastNameTextView.setText(sharedpreferences.getString(getResources().getString(R.string.last_name_sign_up), ""));
            }
            //  email
            if (sharedpreferences.contains(getResources().getString(R.string.email_sign_up))) {
                emailEditText.setText(sharedpreferences.getString(getResources().getString(R.string.email_sign_up), ""));
            }

            //  DOB
            if (sharedpreferences.contains(getResources().getString(R.string.dob_sign_up))) {
                dobEditText.setText(sharedpreferences.getString(getResources().getString(R.string.dob_sign_up), ""));
            }

            //  gender
            if (sharedpreferences.contains(getResources().getString(R.string.gender))) {
                boolean isMale = sharedpreferences.getBoolean(getResources().getString(R.string.gender), false);

                RadioButton male = findViewById(R.id.gender_male);
                RadioButton female = findViewById(R.id.gender_female);
                if (isMale)
                    male.setChecked(true);
                else
                    female.setChecked(true);
            }

            //  ICE
            if (sharedpreferences.contains(getResources().getString(R.string.ice_name_sign_up))) {
                iceNameEditText.setText(sharedpreferences.getString(getResources().getString(R.string.ice_name_sign_up), ""));
            }

            //  ICE Phone
            if (sharedpreferences.contains(getResources().getString(R.string.ice_phone_number_sign_up))) {
                icePhoneEditText.setText(sharedpreferences.getString(getResources().getString(R.string.ice_phone_number_sign_up), ""));
            }

            //  height
            if (sharedpreferences.contains(getResources().getString(R.string.height_sign_up))) {
                heightEditText.setText(sharedpreferences.getString(getResources().getString(R.string.height_sign_up), ""));
            }

            //  weight
            if (sharedpreferences.contains(getResources().getString(R.string.weight_sign_up))) {
                weightEditText.setText(sharedpreferences.getString(getResources().getString(R.string.weight_sign_up), ""));
            }

            //  PCP Name
            if (sharedpreferences.contains(getResources().getString(R.string.pcp_name_sign_up))) {
                pcpNameEditText.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_name_sign_up), ""));
            }

            if (sharedpreferences.contains(getResources().getString(R.string.pcp_address_sign_up))) {
                pcpAddressEditText.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_address_sign_up), ""));
            }

            if (sharedpreferences.contains(getResources().getString(R.string.pcp_city_sign_up))) {
                pcpCityEditText.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_city_sign_up), ""));
            }
            //  PCP State
            if (sharedpreferences.contains(getResources().getString(R.string.pcp_state_sign_up))) {
                String st = sharedpreferences.getString(getResources().getString(R.string.pcp_state_sign_up), "");
                if (!st.isEmpty()) {
                    ArrayList<String> stateList = PopulateSpinner.GetStates();
                    Collections.sort(stateList);
                    int position = stateList.indexOf(st);
                    stateSpinner.setSelection(position);
                }

            }
            //  PCP Zip
            if (sharedpreferences.contains(getResources().getString(R.string.pcp_zip_sign_up))) {
                pcpZipEditText.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_zip_sign_up), ""));
            }
            //  PCP Phone
            if (sharedpreferences.contains(getResources().getString(R.string.pcp_phone_sign_up))) {
                pcpPhoneEditText.setText(sharedpreferences.getString(getResources().getString(R.string.pcp_phone_sign_up), ""));
            }
        }
        catch (Exception ex) {
            Crashlytics.logException(new Exception("Exception when loading SignUp screen values"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                InputStream imageStream;
                imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView = findViewById(R.id.profileImageView);
                imageView.setImageBitmap(yourSelectedImage);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void populateState(Spinner stateSpinner) {

        ArrayList<String> stateList = PopulateSpinner.GetStates();
        Collections.sort(stateList);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.state_sign_up_spinner) {
            state = parent.getItemAtPosition(position).toString();
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    private String imageToString() {

        ImageView imageView = findViewById(R.id.profileImageView);
        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        return ImageConverter.imageToString(bitmap);
    }

    private boolean checkDataEntered()
    {
        boolean allClear = true;

        if (isEmpty(firstNameEditText)) {
            allClear = false;
            firstNameEditText.requestFocus();
            firstNameEditText.setError("FirstName is required");
        }
        if (isEmpty(lastNameTextView)) {
            if (allClear) lastNameTextView.requestFocus();
            allClear = false;
            lastNameTextView.setError("LastName is required");
        }
        if (isEmpty(dobEditText)) {
            if (allClear) dobEditText.requestFocus();
            allClear = false;
            dobEditText.setError("Dat of Birth is required");


        }
        if (isEmpty(emailEditText)) {
            if (allClear) emailEditText.requestFocus();
            allClear = false;
            emailEditText.setError("Email is required");
        }
        if (isEmpty(heightEditText)) {
            if (allClear) heightEditText.requestFocus();
            allClear = false;
            heightEditText.setError("Height is required");
        }
        if (isEmpty(weightEditText)) {
            if (allClear) weightEditText.requestFocus();
            allClear = false;
            weightEditText.setError("Weight is required");
        }

        return allClear;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Landing Activity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void createAccount() {

       if (checkDataEntered()) {

           boolean isMale = true;
           RadioGroup gender = findViewById(R.id.genderRadioGroup);
           int selectedId = gender.getCheckedRadioButtonId();
           if (selectedId == R.id.gender_female) {
               isMale = false;
           }
           SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
           SharedPreferences.Editor editor = pref.edit();
           editor.putString(getResources().getString(R.string.profile_picture), imageToString());

           if (!firstNameEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.first_name_sign_up), firstNameEditText.getText().toString());
           if (!lastNameTextView.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.last_name_sign_up), lastNameTextView.getText().toString());
           if (!dobEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.dob_sign_up), dobEditText.getText().toString());
           if (!emailEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.email_sign_up), emailEditText.getText().toString());
//           if (!phoneEditText.getText().toString().isEmpty())
//               editor.putString(getResources().getString(R.string.phone_number_sign_up), phoneEditText.getText().toString());
               editor.putBoolean(getResources().getString(R.string.gender), isMale);
           if (!iceNameEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.ice_name_sign_up), iceNameEditText.getText().toString());
           if (!icePhoneEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.ice_phone_number_sign_up), icePhoneEditText.getText().toString());
           if (!heightEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.height_sign_up), heightEditText.getText().toString());
           if (!weightEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.weight_sign_up), weightEditText.getText().toString());

           if (!pcpNameEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.pcp_name_sign_up), pcpNameEditText.getText().toString());
           if (!pcpAddressEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.pcp_address_sign_up), pcpAddressEditText.getText().toString());
           if (!pcpCityEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.pcp_city_sign_up), pcpCityEditText.getText().toString());
           editor.putString(getResources().getString(R.string.pcp_state_sign_up), state);
           if (!pcpZipEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.pcp_zip_sign_up), pcpZipEditText.getText().toString());
           if (!pcpPhoneEditText.getText().toString().isEmpty())
               editor.putString(getResources().getString(R.string.pcp_phone_sign_up), pcpPhoneEditText.getText().toString());

           editor.commit();

           Intent intent = new Intent(this, OptionsActivity.class);
           startActivity(intent);
       }
    }


    @Override
    public void onClick(View v) {
        if (v == dobEditText) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> dobEditText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year), mYear, mMonth, mDay);

            datePickerDialog.show();
        }

    }
}
