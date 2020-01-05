package com.udacity.capstone.trackmyhealth.database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicationDao {

    @Query("SELECT * FROM medication ORDER BY ID")
    LiveData<List<Medication>> getAllMedications();

    @Query("SELECT COUNT(ID) FROM medication")
    int getCount();

    @Insert
    void insert(Medication movie);

    @Update
    void updatePerson(Medication person);

    @Delete
    void delete(Medication movie);

    @Query("SELECT * FROM medication WHERE id = :id")
    Medication loadMedicationById(int id);
}
