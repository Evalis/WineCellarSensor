package com.example.winecellarsensor.apis;

import com.example.winecellarsensor.model.Cellar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CellarResponse {

    @SerializedName("cellar")
    @Expose
    private Cellar cellar = null;

    public Cellar getCellar() {
        return cellar;
    }

}