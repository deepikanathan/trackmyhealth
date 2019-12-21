package com.udacity.capstone.trackmyhealth.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
//import android.preference.ListPreference;
//import android.preference.PreferenceManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.udacity.capstone.trackmyhealth.R;
import java.util.Iterator;
import java.util.Map;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preferences);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String pref = sharedPreferences.getString(getString(R.string.height_key),getString(R.string.pounds_value));

        Iterator iter = sharedPreferences.getAll().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry)iter.next();
            if(pair.getValue().equals(pref)){
                setListSummary(sharedPreferences,pair.getKey().toString());
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setListSummary(sharedPreferences,key);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void setListSummary(SharedPreferences sharedPreferences, String key){
        Preference preference = findPreference(key);
        if(preference == null){
            return;
        }
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            listPreference.setSummary(listPreference.getEntry());
        }
    }
}
