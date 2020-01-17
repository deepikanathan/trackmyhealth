package com.udacity.capstone.trackmyhealth.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.ui.MainActivity;
import com.udacity.capstone.trackmyhealth.utils.Prefs;

import com.udacity.capstone.trackmyhealth.models.HealthData;


public class HealthDataWidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
        HealthData healthData = Prefs.GetHealthDataFromPreference(context);
        if (healthData != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.healthdata_app_widget);

            views.setTextViewText(R.id.a1c_widget, healthData.getA1c());
            views.setTextViewText(R.id.bloodsugar_widget, healthData.getBloodsugar());
            views.setTextViewText(R.id.triglycerides_widget, healthData.getTriglycerides());
            views.setTextViewText(R.id.weight_widget, healthData.getWeight());
            views.setTextViewText(R.id.hdl_widget, healthData.getHdl());
            views.setTextViewText(R.id.ldl_widget, healthData.getLdl());
            views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

            Intent intent = new Intent(context, HealthDataWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            //views.setRemoteAdapter(R.id.healthdata_widget_data, intent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
            //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.healthdata_widget_data);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateHealthDataWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // Perform any action when one or more AppWidget instances have been deleted
    }

    @Override
    public void onEnabled(Context context) {
        // Perform any action when an AppWidget for this provider is instantiated
    }

    @Override
    public void onDisabled(Context context) {
        // Perform any action when the last AppWidget instance for this provider is deleted
    }
}

