package com.udacity.capstone.trackmyhealth.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.utils.ImageConverter;
import com.udacity.capstone.trackmyhealth.utils.PopulateSpinner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    String heightUnit, weightUnit, state, country;
    String userProfilePicture;

    @BindView (R.id.profileImageView)
    ImageView profileImageView;
    @BindView(R.id.addAccount)
    Button signUpButton;
    @BindView(R.id.weight_unit_spinner)
    Spinner weightSpinner;
    @BindView(R.id.height_unit_spinner)
    Spinner heightSpinner;
    @BindView(R.id.state_sign_up_spinner)
    Spinner stateSpinner;
//    @BindView(R.id.country_sign_up_spinner)
//    Spinner countrySpinner;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
                }
        });

        Picasso.get()
                .load(R.drawable.user_48x48)
                .placeholder(R.drawable.user_48x48)
                .into(profileImageView);

        heightSpinner.setOnItemSelectedListener(this);
        populateHeightUnits(heightSpinner);

        weightSpinner.setOnItemSelectedListener(this);
        populateWeightUnits(weightSpinner);

        stateSpinner.setOnItemSelectedListener(this);
        populateState(stateSpinner);

//        countrySpinner.setOnItemSelectedListener(this);
//        populateCountry(countrySpinner);

        Button buttonLoadImage = findViewById(R.id.importProfilePicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView = findViewById(R.id.profileImageView);
                imageView.setImageBitmap(yourSelectedImage);
                //userProfilePicture = imageStream;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    // Creating adapter for height units spinner
    private void populateHeightUnits(Spinner heightSpinner) {

        ArrayAdapter<CharSequence> heightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.height_units_array, android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        heightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        heightSpinner.setAdapter(heightDataAdapter);
    }

    // Creating adapter for weight units spinner
    private void populateWeightUnits(Spinner weightSpinner) {

        ArrayAdapter<CharSequence> weightDataAdapter =  ArrayAdapter.createFromResource(this, R.array.weight_units_array, android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        weightDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        weightSpinner.setAdapter(weightDataAdapter);
    }

    private void populateState(Spinner stateSpinner) {

        ArrayList<String> stateList = PopulateSpinner.GetStates();
        Collections.sort(stateList);

        ArrayAdapter<String> stateAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stateList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    private void populateCountry(Spinner countrySpinner) {

        ArrayList<String> countryList = PopulateSpinner.GetCountry();
        Collections.sort(countryList);

        ArrayAdapter<String> countryDataAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList);
        countryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryDataAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.height_unit_spinner:
                heightUnit = parent.getItemAtPosition(position).toString();
                break;
            case R.id.weight_unit_spinner:
                weightUnit = parent.getItemAtPosition(position).toString();
                break;
            case R.id.state_sign_up_spinner:
                state = parent.getItemAtPosition(position).toString();
                break;
//            case R.id.country_sign_up_spinner:
//                country = parent.getItemAtPosition(position).toString();
//                break;
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

    public void CreateAccount() {

        boolean isMale = true;
        RadioGroup gender = findViewById(R.id.genderRadioGroup);
        int selectedId = gender.getCheckedRadioButtonId();
        if (selectedId == R.id.gender_female) {
            isMale = false;
        }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putString(getResources().getString(R.string.profile_picture), imageToString());




        editor.putString(getResources().getString(R.string.first_name_sign_up), ((EditText)findViewById(R.id.firstNameEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.last_name_sign_up), ((EditText)findViewById(R.id.lastNameTextView)).getText().toString());
        editor.putString(getResources().getString(R.string.dob_sign_up), ((EditText)findViewById(R.id.dobEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.email_sign_up), ((EditText)findViewById(R.id.emailEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.phone_number_sign_up), ((EditText)findViewById(R.id.phoneEditText)).getText().toString());
        editor.putBoolean(getResources().getString(R.string.gender), isMale);
        editor.putString(getResources().getString(R.string.ice_name_sign_up), ((EditText)findViewById(R.id.iceNameEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.ice_phone_number_sign_up), ((EditText)findViewById(R.id.icePhoneEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.height_sign_up), ((EditText)findViewById(R.id.heightEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.weight_sign_up), ((EditText)findViewById(R.id.weightEditText)).getText().toString());

        editor.putString(getResources().getString(R.string.pcp_name_sign_up), ((EditText)findViewById(R.id.pcpNameEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.pcp_address_sign_up), ((EditText)findViewById(R.id.pcpAddressEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.pcp_city_sign_up), ((EditText)findViewById(R.id.pcpCityEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.pcp_state_sign_up), state);
//        editor.putString(getResources().getString(R.string.pcp_country_sign_up), country);
        editor.putString(getResources().getString(R.string.pcp_zip_sign_up), ((EditText)findViewById(R.id.pcpZipEditText)).getText().toString());
        editor.putString(getResources().getString(R.string.pcp_phone_sign_up), ((EditText)findViewById(R.id.pcpPhoneEditText)).getText().toString());

        editor.commit();

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
