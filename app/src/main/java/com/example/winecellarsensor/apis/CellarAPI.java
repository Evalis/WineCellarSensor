package com.example.winecellarsensor.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CellarAPI {
    @GET("/api/cellar/GetCurrentRoomValue/{cellarID}")
    Call<CellarResponse> getAllRooms(@Path("cellarID") String cellarID);

    @GET("/api/cellar/GetWeeklyAverageByRoom/{cellarID}/{roomName}")
    Call<MeasurementsResponse> getAllWeeklyMeasurements(@Path("roomName") String roomName, @Path("cellarID") String cellarID);

    @GET("/api/cellar/GetMonthlyAverageByRoom/{cellarID}/{roomName}")
    Call<MeasurementsResponse> getAllMonthlyMeasurements(@Path("roomName")String roomName, @Path("cellarID")String cellarID);

    @GET("/api/cellar/GetAllDailyRoomValue/{cellarID}/{roomName}")
    Call<MeasurementsResponse> getAllDailyMeasurements (@Path("roomName")String roomName, @Path("cellarID")String cellarID);

}



