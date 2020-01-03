package com.udacity.capstone.trackmyhealth.database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM medication")
    LiveData<List<Medication>> getAll();

    @Query("SELECT COUNT(ID) FROM medication")
    int getCount();

    @Insert
    void insert(Medication movie);

    @Delete
    void delete(Medication movie);
}
