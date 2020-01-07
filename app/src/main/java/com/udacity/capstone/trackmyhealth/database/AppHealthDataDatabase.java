package com.udacity.capstone.trackmyhealth.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HealthData.class}, version = 1, exportSchema = false)
public abstract class AppHealthDataDatabase extends RoomDatabase {

    public abstract HealthDataDao healthDataDao();

    private static AppHealthDataDatabase INSTANCE;


    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "healthDataList";
    private static AppHealthDataDatabase sInstance;

    public static AppHealthDataDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppHealthDataDatabase.class, AppHealthDataDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }
}
