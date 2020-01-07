package com.udacity.capstone.trackmyhealth.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class AppMedicationDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();
    private static AppMedicationDatabase INSTANCE;


    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "medicationList";
    private static AppMedicationDatabase sInstance;

    public static AppMedicationDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppMedicationDatabase.class, AppMedicationDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

}
