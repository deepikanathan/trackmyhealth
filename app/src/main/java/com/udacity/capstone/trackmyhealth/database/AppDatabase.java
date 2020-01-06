package com.udacity.capstone.trackmyhealth.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.udacity.capstone.trackmyhealth.R;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();

    private static AppDatabase INSTANCE;


    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "medicationList";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

}
