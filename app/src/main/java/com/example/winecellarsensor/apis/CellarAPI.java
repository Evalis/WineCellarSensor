package com.example.winecellarsensor.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CellarAPI {
    @GET("/api/sensor/get/{cellarID}")
    Call<CellarResponse> getCellar(@Path("cellarID") String cellarID);
}



