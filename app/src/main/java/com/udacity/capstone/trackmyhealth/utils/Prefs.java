package com.udacity.capstone.trackmyhealth.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.HealthData;

public class Prefs {
    public static final String PREFS_NAME = "prefs";

    public static void AddHealthDataToPreference(Context context, HealthData healthData) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.healthdata_pref_key), StringUtil.toBase64String(healthData));
        prefs.apply();
    }

    public static HealthData GetHealthDataFromPreference(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String healthBase64 = prefs.getString(context.getString(R.string.healthdata_pref_key), "");
        return "".equals(healthBase64) ? null : StringUtil.fromBase64(prefs.getString(context.getString(R.string.healthdata_pref_key), ""));
    }
}
