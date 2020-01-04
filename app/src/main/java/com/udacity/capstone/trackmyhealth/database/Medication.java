package com.udacity.capstone.trackmyhealth.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "medication")

public class Medication {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "dose")
    private String dose;

    @ColumnInfo(name = "unit")
    private String unit;

    @ColumnInfo(name = "frequency")
    private String frequency;

    public Medication() {
    }

    @Ignore
    public Medication(String name, String dose, String unit, String frequency) {
        this.name = name;
        this.dose = dose;
        this.unit = unit;
        this.frequency = frequency;
    }

    public Medication(int id, String name, String dose, String unit, String frequency) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.unit = unit;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    @Override
    public boolean equals(Object obj) {
        Medication m = (Medication) obj;
        if(id == m.getId()){
            return true;
        }else{
            return false;
        }
    }


}

