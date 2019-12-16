package com.example.winecellarsensor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Measurements {

    @SerializedName("RoomName")
    @Expose
    private String roomName;

    @SerializedName("CO2Value")
    @Expose
    private List<Co2> co2List;

    @SerializedName("TempValue")
    @Expose
    private List<Temperature> temperatureList;

    @SerializedName("HumValue")
    @Expose
    private List<Humidity> humidityList;

    public Measurements(String roomName, List<Co2> co2List, List<Temperature> temperatureList, List<Humidity> humidityList) {
        this.roomName = roomName;
        this.co2List = co2List;
        this.temperatureList = temperatureList;
        this.humidityList = humidityList;
    }

    public Measurements(){
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Co2> getCo2List() {
        return co2List;
    }

    public void setCo2List(List<Co2> co2List) {
        this.co2List = co2List;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public List<Humidity> getHumidityList() {
        return humidityList;
    }

    public void setHumidityList(List<Humidity> humidityList) {
        this.humidityList = humidityList;
    }
}
