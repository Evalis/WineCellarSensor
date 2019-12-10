package com.example.winecellarsensor.apis;

import com.example.winecellarsensor.model.WineCellar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CellarResponse {

    @SerializedName("WineCellars")
    @Expose
    private List<WineCellar> wineCellars = null;

    public List<WineCellar> getWineCellars() {
        return wineCellars;
    }

}