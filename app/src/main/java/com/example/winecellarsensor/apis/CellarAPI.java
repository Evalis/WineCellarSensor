package com.example.winecellarsensor.apis;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CellarAPI {
    @GET("/getAll/123")
    Call<CellarResponse> getCellar();
}



