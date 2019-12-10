package com.example.winecellarsensor.repositories;

import android.app.Application;
import android.util.Log;

import com.example.winecellarsensor.apis.CellarAPI;
import com.example.winecellarsensor.apis.CellarResponse;
import com.example.winecellarsensor.apis.CellarServiceGenerator;
import com.example.winecellarsensor.model.Cellar;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellarRepository {

    private static CellarRepository instance;
    private MutableLiveData<Cellar> cellar;

    private CellarRepository(Application application)
    {
        cellar = new MutableLiveData<>();

    }

    public static synchronized CellarRepository getInstance( Application application) {
        if (instance == null) {
            instance = new CellarRepository(application);
        }
        return instance;
    }

    public LiveData<Cellar> getCellar(){
        return cellar;
    }

    public void updateCellar() {
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<CellarResponse> call = cellarAPI.getCellar();
        call.enqueue(new Callback<CellarResponse>() {
            @Override
            public void onResponse(Call<CellarResponse> call, Response<CellarResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    cellar.setValue(response.body().getCellar());
                }
            }

            @Override
            public void onFailure(Call<CellarResponse> call, Throwable t) {
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }
}
