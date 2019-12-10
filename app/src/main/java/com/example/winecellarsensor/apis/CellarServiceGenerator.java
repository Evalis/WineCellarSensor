package com.example.winecellarsensor.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CellarServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://10.152.222.33:8080/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CellarAPI cellarAPI = retrofit.create(CellarAPI.class);

    public static CellarAPI getCellarAPI() {
        return cellarAPI;
    }

}