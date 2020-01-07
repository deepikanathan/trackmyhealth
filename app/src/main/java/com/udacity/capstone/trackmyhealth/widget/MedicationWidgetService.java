package com.udacity.capstone.trackmyhealth.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.udacity.capstone.trackmyhealth.database.Medication;
import com.udacity.capstone.trackmyhealth.utils.Prefs;


public class MedicationWidgetService extends RemoteViewsService {

    public static void updateWidget(Context context, Medication medication) {
        Prefs.AddRecipeToPreference(context, medication);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MedicationWidgetProvider.class));
        MedicationWidgetProvider.updateMedicationWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        return new ListRemoteViewsFactory(getApplicationContext());
    }

}