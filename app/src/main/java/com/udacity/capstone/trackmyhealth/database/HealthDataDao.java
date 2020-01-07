package com.udacity.capstone.trackmyhealth.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HealthDataDao {

    @Query("SELECT * FROM HEALTHDATA ORDER BY ID")
    LiveData<List<HealthData>> getAllHealthData();

    @Query("SELECT COUNT(ID) FROM healthdata")
    int getCount();

    @Insert
    void insert(HealthData healthData);

    @Update
    void update(HealthData healthData);

    @Delete
    void delete(HealthData healthData);

    @Query("SELECT * FROM HEALTHDATA WHERE id = :id")
    HealthData loadHealthDataById(int id);
}
