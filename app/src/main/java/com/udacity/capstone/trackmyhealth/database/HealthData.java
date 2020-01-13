package com.udacity.capstone.trackmyhealth.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "healthdata")

public class HealthData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name="docname")
    private String docname;

    @ColumnInfo(name="dateofvisit")
    private String dateofvisit;

    @ColumnInfo(name="a1c")
    private String a1c;

    @ColumnInfo(name="bloodsugar")
    private String bloodsugar;

    @ColumnInfo(name="triglycerides")
    private String triglycerides;

    @ColumnInfo(name="weight")
    private String weight;

    @ColumnInfo(name="hdl")
    private String hdl;

    @ColumnInfo(name="ldl")
    private String ldl;

    @Ignore
    public HealthData(String docname, String dateofvisit, String a1c, String bloodsugar, String triglycerides, String weight, String hdl, String ldl) {
        this.docname = docname;
        this.dateofvisit = dateofvisit;
        this.a1c = a1c;
        this.bloodsugar = bloodsugar;
        this.triglycerides = triglycerides;
        this.weight = weight;
        this.hdl = hdl;
        this.ldl = ldl;
    }

    public HealthData(int id, String docname, String dateofvisit, String a1c, String bloodsugar, String triglycerides, String weight, String hdl, String ldl) {
        this.id = id;
        this.docname = docname;
        this.dateofvisit = dateofvisit;
        this.a1c = a1c;
        this.bloodsugar = bloodsugar;
        this.triglycerides = triglycerides;
        this.weight = weight;
        this.hdl = hdl;
        this.ldl = ldl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDateofvisit() {
        return dateofvisit;
    }

    public void setDateofvisit(String dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    public String getA1c() {
        return a1c;
    }

    public void setA1c(String a1c) {
        this.a1c = a1c;
    }

    public String getBloodsugar() {
        return bloodsugar;
    }

    public void setBloodsugar(String bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    public String getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(String triglycerides) {
        this.triglycerides = triglycerides;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHdl() {
        return hdl;
    }

    public void setHdl(String hdl) {
        this.hdl = hdl;
    }

    public String getLdl() {
        return ldl;
    }

    public void setLdl(String ldl) {
        this.ldl = ldl;
    }

    @Override
    public boolean equals(Object obj) {
        HealthData m = (HealthData) obj;
        return id == m.getId();
    }
}
