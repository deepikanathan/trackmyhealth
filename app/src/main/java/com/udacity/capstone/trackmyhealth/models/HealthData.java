package com.udacity.capstone.trackmyhealth.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HealthData implements Parcelable {

    private String docname;
    private String dateofvisit;
    private String a1c;
    private String bloodsugar;
    private String triglycerides;
    private String weight;
    private String hdl;
    private String ldl;

    public static final Creator<HealthData> CREATOR = new Creator<HealthData>() {
        @Override
        public HealthData createFromParcel(Parcel in) {
            return new HealthData(in);
        }

        @Override
        public HealthData[] newArray(int size) {
            return new HealthData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.docname);
        parcel.writeString(this.dateofvisit);
        parcel.writeString(this.a1c);
        parcel.writeString(this.bloodsugar);
        parcel.writeString(this.triglycerides);
        parcel.writeString(this.weight);
        parcel.writeString(this.hdl);
        parcel.writeString(this.ldl);
    }

    public HealthData() {
        this.docname = "";
        this.dateofvisit = "";
        this.a1c = "";
        this.bloodsugar = "";
        this.triglycerides = "";
        this.weight = "";
        this.hdl = "";
        this.ldl = "";
    }

    public HealthData(String a1c, String bloodsugar, String dateofvisit, String docname, String hdl, String ldl, String triglycerides, String weight) {
        this.docname = docname;
        this.dateofvisit = dateofvisit;
        this.a1c = a1c;
        this.bloodsugar = bloodsugar;
        this.triglycerides = triglycerides;
        this.weight = weight;
        this.hdl = hdl;
        this.ldl = ldl;
    }

    protected HealthData(Parcel in) {
        this.docname = in.readString();
        this.dateofvisit = in.readString();
        this.a1c = in.readString();
        this.bloodsugar = in.readString();
        this.triglycerides = in.readString();
        this.weight = in.readString();
        this.hdl = in.readString();
        this.ldl = in.readString();
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
}
