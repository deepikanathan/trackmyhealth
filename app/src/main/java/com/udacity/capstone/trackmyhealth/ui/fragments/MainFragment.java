package com.udacity.capstone.trackmyhealth.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.ui.LandingActivity;
import com.udacity.capstone.trackmyhealth.ui.MainActivity;
import com.udacity.capstone.trackmyhealth.ui.SignUpActivity;
import com.udacity.capstone.trackmyhealth.utils.FetchMedicationTask;
import com.udacity.capstone.trackmyhealth.utils.NetworkUtils;

public class MainFragment extends Fragment {

    private static final String mypreference = "User";
    boolean isFirstTimeAccess = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            SharedPreferences prefs = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            if (prefs != null) {
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
        if (!isFirstTimeAccess) {
            showLandingActivity(viewRoot);
        } else {
            showOptionsActivity(viewRoot);
        }
        return viewRoot;
    }

    private View showOptionsActivity(View viewRoot) {
        LinearLayout options = viewRoot.findViewById(R.id.options);
        options.setVisibility(View.VISIBLE);
        LinearLayout landing = viewRoot.findViewById(R.id.landing);
        landing.setVisibility(View.GONE);
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
