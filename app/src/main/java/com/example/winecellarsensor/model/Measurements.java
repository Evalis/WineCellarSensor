package com.example.winecellarsensor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.room.Entity;

@Entity(tableName = "measurements_table")
public class Measurements implements Serializable {

    private List<Co2> co2;
    private List<Temperature> temperature;
    private List<Humidity> humidity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    public List<Co2> getCo2() {
        return co2;
    }
    public void setCo2(List<Co2> co2) {
        this.co2 = co2;
    }
    public List<Temperature> getTemperature() {
        return temperature;
    }
    public void setTemperature(List<Temperature> temperature) {
        this.temperature = temperature;
    }
    public List<Humidity> getHumidity() {
        return humidity;
    }
    public void setHumidity(List<Humidity> humidity) {
        this.humidity = humidity;
    }
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
