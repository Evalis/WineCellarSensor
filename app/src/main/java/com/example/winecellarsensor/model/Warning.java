package com.example.winecellarsensor.model;

import java.io.Serializable;
import java.util.Date;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "warning_table")
public class Warning implements Serializable {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private String cellarId;
  private String roomName;
  private String sensorName;
  @TypeConverters(DateConverter.class)
  private Date date;
  private double maxValue;
  private double minValue;
  private double actualValue;

  public Warning(String roomName, String sensorName, Date date, double maxValue, double minValue, double actualValue) {

    this.roomName = roomName;
    this.sensorName = sensorName;
    this.date = date;
    this.maxValue = maxValue;
    this.minValue = minValue;
    this.actualValue = actualValue;

  }

  public Warning()
  {

  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setCellarId(String cellarId) {
    this.cellarId = cellarId;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setMaxValue(double maxValue) {
    this.maxValue = maxValue;
  }

  public void setMinValue(double minValue) {
    this.minValue = minValue;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public void setSensorName(String sensorName) {
    sensorName = sensorName;
  }
  public void setActualValue(double actualValue) {
    this.actualValue = actualValue;
  }

  public Date getDate() {
    return date;
  }

  public double getActualValue() {
    return actualValue;
  }

  public double getMaxValue() {
    return maxValue;
  }

  public double getMinValue() {
    return minValue;
  }

  public String getCellarId() {
    return cellarId;
  }

  public String getRoomName() {
    return roomName;
  }

  public String getSensorName() {
    return sensorName;
  }
}

