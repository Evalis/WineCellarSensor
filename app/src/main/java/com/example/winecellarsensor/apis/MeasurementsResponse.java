package com.example.winecellarsensor.apis;

import com.example.winecellarsensor.model.Co2;
import com.example.winecellarsensor.model.Humidity;
import com.example.winecellarsensor.model.Measurements;
import com.example.winecellarsensor.model.Temperature;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeasurementsResponse {

    @SerializedName("RoomName")
    @Expose
    private String roomName;
    @SerializedName("CO2Value")
    @Expose
    private List<Co2> co2Value;
    @SerializedName("TempValue")
    @Expose
    private List<Temperature> tempValue;
    @SerializedName("HumValue")
    @Expose
    private List<Humidity> humValue;

    public Measurements getAllWeeklyMeasurements(){
        return new Measurements(roomName,co2Value,tempValue,humValue);
    }

    public Measurements getAllMonthlyMeasurements(){
        return new Measurements(roomName,co2Value, tempValue, humValue);
    }
}
