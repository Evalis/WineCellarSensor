package com.example.winecellarsensor.apis;

import com.example.winecellarsensor.model.Room;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CellarResponse {

    @SerializedName("Rooms")
    @Expose
    private List<Room> rooms = null;

    public List<com.example.winecellarsensor.model.Room> getRooms() {
        return rooms;
    }

}