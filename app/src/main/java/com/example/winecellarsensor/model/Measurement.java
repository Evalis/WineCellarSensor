package com.example.winecellarsensor.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Measurement {

    @SerializedName("M_ID")
    @Expose
    private String mID;
    @SerializedName("DateInserted")
    @Expose
    private Date dateInserted;
    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("DataValue")
    @Expose
    private String dataValue;
    @SerializedName("SensorID")
    @Expose
    private String sensorID;

    public String getMID() {
        return mID;
    }

    public void setMID(String mID) {
        this.mID = mID;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

}