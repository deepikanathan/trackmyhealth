package com.udacity.capstone.trackmyhealth.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.udacity.capstone.trackmyhealth.R;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{

    public abstract MovieDao movieDao();
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, context.getResources().getString(R.string.medication_database))
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
