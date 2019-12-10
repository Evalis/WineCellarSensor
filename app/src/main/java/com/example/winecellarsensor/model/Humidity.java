package com.example.winecellarsensor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import androidx.room.Entity;

@Entity(tableName = "humidity_table")
public class Humidity implements Serializable {


    private String datetime;
    private Integer value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Humidity(String datetime, Integer value)
    {
        this.datetime = datetime;
        this.value = value;
    }
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
