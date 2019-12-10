package com.example.winecellarsensor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import androidx.room.Entity;

@Entity(tableName = "room_table")
public class Room implements Serializable {

    private String roomName;
    private Measurements measurements;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Room (String roomName,Measurements measurements)
    {
        this.roomName = roomName;
        this. measurements = measurements;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
