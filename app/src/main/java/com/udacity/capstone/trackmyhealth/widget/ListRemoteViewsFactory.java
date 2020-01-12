package com.udacity.capstone.trackmyhealth.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.HealthData;
import com.udacity.capstone.trackmyhealth.utils.Prefs;

/**
 * Adapter for the widget
 */
//  REf - https://www.sitepoint.com/killer-way-to-show-a-list-of-items-in-android-collection-widget/
public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private HealthData healthData;

    public ListRemoteViewsFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        healthData = Prefs.GetHealthDataFromPreference(mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.healthdata_app_widget_list_item);

        row.setTextViewText(R.id.healthdata_text, healthData.getA1c());

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
