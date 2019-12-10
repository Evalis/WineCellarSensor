package com.example.winecellarsensor.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CellarServiceGenerator {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://sep4.gear.host/")
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CellarAPI cellarAPI = retrofit.create(CellarAPI.class);

    public static CellarAPI getCellarAPI() {
        return cellarAPI;
    }

}