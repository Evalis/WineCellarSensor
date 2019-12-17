package com.example.winecellarsensor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Room implements Serializable {

    @SerializedName("RoomName")
    @Expose
    private String roomName;
    @SerializedName("CO2Value")
    @Expose
    private Co2 co2;
    @SerializedName("TempValue")
    @Expose
    private Temperature temperature;
    @SerializedName("HumValue")
    @Expose
    private Humidity humidity;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Co2 getCo2() {
        return co2;
    }

    public void setCo2(Co2 co2) {
        this.co2 = co2;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

}
