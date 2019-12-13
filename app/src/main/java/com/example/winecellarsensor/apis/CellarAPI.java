package com.example.winecellarsensor.apis;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CellarAPI {
    @GET("/api/cellar/GetCurrentRoomValue/{cellarID}")
    Call<CellarResponse> getAllRooms(@Path("cellarID") String cellarID);
}



