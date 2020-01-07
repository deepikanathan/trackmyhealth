package com.udacity.capstone.trackmyhealth.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.Medication;

public class Prefs {
    public static final String PREFS_NAME = "prefs";

    public static void AddRecipeToPreference(Context context, Medication medication) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.medication_pref_key), StringUtil.toBase64String(medication));
        prefs.apply();
    }

    public static Medication GetMedicationFromPreference(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String recipeBase64 = prefs.getString(context.getString(R.string.medication_pref_key), "");
        return "".equals(recipeBase64) ? null : StringUtil.fromBase64(prefs.getString(context.getString(R.string.medication_pref_key), ""));
    }
}
