package com.udacity.capstone.trackmyhealth.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.analytics.AnalyticsApplication;
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.ui.HealthDataActivity;
import com.udacity.capstone.trackmyhealth.ui.LandingActivity;
import com.udacity.capstone.trackmyhealth.ui.MainActivity;
import com.udacity.capstone.trackmyhealth.ui.MedicationsActivity;
import com.udacity.capstone.trackmyhealth.ui.OptionsActivity;
import com.udacity.capstone.trackmyhealth.ui.ProfileActivity;
import com.udacity.capstone.trackmyhealth.ui.SignUpActivity;
import com.udacity.capstone.trackmyhealth.utils.FetchMedicationTask;
import com.udacity.capstone.trackmyhealth.utils.NetworkUtils;

public class MainFragment extends Fragment {

    boolean isFirstTimeAccess = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            SharedPreferences prefs = getActivity().getSharedPreferences(Constants.mypreference, Context.MODE_PRIVATE);
            if (prefs != null && prefs.contains(getString(R.string.first_name_sign_up))) {
                isFirstTimeAccess = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        if (isFirstTimeAccess) {
            showLandingActivity(viewRoot);
        } else {
            showOptionsActivity(viewRoot);
        }
        return viewRoot;
    }

    private View showOptionsActivity(View viewRoot) {
        LinearLayout landing = viewRoot.findViewById(R.id.landing);
        landing.setVisibility(View.GONE);

        LinearLayout options = viewRoot.findViewById(R.id.options);
        options.setVisibility(View.VISIBLE);


        Button profileButton = options.findViewById(R.id.profile_button);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        });

        Button journalButton = options.findViewById(R.id.journal_button);
        journalButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), HealthDataActivity.class);
            startActivity(intent);
        });

        Button medicationsButton = options.findViewById(R.id.medications_button);
        medicationsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), MedicationsActivity.class);
            startActivity(intent);
        });

        return viewRoot;
    }

    private View showLandingActivity(View viewRoot) {
        LinearLayout options = viewRoot.findViewById(R.id.options);
        options.setVisibility(View.GONE);

        LinearLayout landing = viewRoot.findViewById(R.id.landing);
        landing.setVisibility(View.VISIBLE);

//        return viewRoot;
        Button signUpButton = landing.findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), SignUpActivity.class);
            startActivity(intent);


        });
        return viewRoot;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
