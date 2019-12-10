package com.example.winecellarsensor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.room.Entity;

@Entity(tableName = "cellar_table")
public class Cellar implements Serializable {

    private String cellarID;
    private String cellarName;
    private List<Room> rooms = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Cellar(){}

    public Cellar (String cellarID, String cellarName)
    {
        this.cellarID = cellarID;
        this.cellarName = cellarName;
    }

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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
