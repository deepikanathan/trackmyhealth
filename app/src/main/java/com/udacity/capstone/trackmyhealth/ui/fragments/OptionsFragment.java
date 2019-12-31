package com.udacity.capstone.trackmyhealth.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.ui.SignUpActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OptionsFragment extends Fragment {

    private Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.activity_options, container, false);
        Button signUpButton = viewRoot.findViewById(R.id.sign_up_button);

            signUpButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                }
            });

       // unbinder = ButterKnife.bind(this, viewRoot);
        return viewRoot;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!TextUtils.isEmpty(step.getVideoURL()))
//            initializePlayer(Uri.parse(step.getVideoURL()));
//        else {
//            nestedScrollView.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
