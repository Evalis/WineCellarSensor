package com.example.winecellarsensor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Temperature implements Serializable {

    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("value")
    @Expose
    private Double value;

    public Temperature(Date date, Double value){
        this.date=date;
        this.value=value;
    }

    public static String TYPE = "Temperature";

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
