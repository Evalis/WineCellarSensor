package com.example.winecellarsensor.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensor {

    @SerializedName("SensorID")
    @Expose
    private String sensorID;
    @SerializedName("Sensorname")
    @Expose
    private String sensorname;
    @SerializedName("WinecellarID")
    @Expose
    private String winecellarID;
    @SerializedName("RoomLocation")
    @Expose
    private String roomLocation;
    @SerializedName("ListOfMeasure")
    @Expose
    private List<Measurement> listOfMeasure = null;

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    public String getSensorname() {
        return sensorname;
    }

    public void setSensorname(String sensorname) {
        this.sensorname = sensorname;
    }

    public String getWinecellarID() {
        return winecellarID;
    }

    public void setWinecellarID(String winecellarID) {
        this.winecellarID = winecellarID;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public List<Measurement> getListOfMeasure() {
        return listOfMeasure;
    }

    public void setListOfMeasure(List<Measurement> listOfMeasure) {
        this.listOfMeasure = listOfMeasure;
    }

}