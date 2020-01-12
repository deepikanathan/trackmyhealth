package com.udacity.capstone.trackmyhealth.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.udacity.capstone.trackmyhealth.database.HealthData;
import com.udacity.capstone.trackmyhealth.utils.Prefs;


public class HealthDataWidgetService extends RemoteViewsService {

    public static void updateWidget(Context context, HealthData healthData) {
        Prefs.AddRecipeToPreference(context, healthData);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, HealthDataWidgetProvider.class));
        HealthDataWidgetProvider.updateHealthDataWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        return new ListRemoteViewsFactory(getApplicationContext());
    }

}