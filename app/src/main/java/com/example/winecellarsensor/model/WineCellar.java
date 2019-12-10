package com.example.winecellarsensor.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WineCellar {

    @SerializedName("CellarID")
    @Expose
    private String cellarID;
    @SerializedName("CellarName")
    @Expose
    private String cellarName;
    @SerializedName("CellarStreetName")
    @Expose
    private String cellarStreetName;
    @SerializedName("CellarZipCode")
    @Expose
    private String cellarZipCode;
    @SerializedName("CellarCity")
    @Expose
    private String cellarCity;
    @SerializedName("CellarCountry")
    @Expose
    private String cellarCountry;
    @SerializedName("SensorList")
    @Expose
    private List<Sensor> sensorList = null;

    public String getCellarID() {
        return cellarID;
    }

    public void setCellarID(String cellarID) {
        this.cellarID = cellarID;
    }

    public String getCellarName() {
        return cellarName;
    }

    public void setCellarName(String cellarName) {
        this.cellarName = cellarName;
    }

    public String getCellarStreetName() {
        return cellarStreetName;
    }

    public void setCellarStreetName(String cellarStreetName) {
        this.cellarStreetName = cellarStreetName;
    }

    public String getCellarZipCode() {
        return cellarZipCode;
    }

    public void setCellarZipCode(String cellarZipCode) {
        this.cellarZipCode = cellarZipCode;
    }

    public String getCellarCity() {
        return cellarCity;
    }

    public void setCellarCity(String cellarCity) {
        this.cellarCity = cellarCity;
    }

    public String getCellarCountry() {
        return cellarCountry;
    }

    public void setCellarCountry(String cellarCountry) {
        this.cellarCountry = cellarCountry;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

}
