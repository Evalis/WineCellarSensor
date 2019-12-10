package com.example.winecellarsensor.repositories;

import android.app.Application;
import android.util.Log;

import com.example.winecellarsensor.apis.CellarAPI;
import com.example.winecellarsensor.apis.CellarResponse;
import com.example.winecellarsensor.apis.CellarServiceGenerator;
import com.example.winecellarsensor.model.WineCellar;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellarRepository {

    private static CellarRepository instance;
    private MutableLiveData<List<WineCellar>> wineCellars;

    private CellarRepository(Application application)
    {
        wineCellars = new MutableLiveData<List<WineCellar>>();

    }

    public static synchronized CellarRepository getInstance( Application application) {
        if (instance == null) {
            instance = new CellarRepository(application);
        }
        return instance;
    }

    public LiveData<List<WineCellar>> getCellar(){
        return wineCellars;
    }

    public void updateCellar(String cellarID) {
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<CellarResponse> call = cellarAPI.getCellar(cellarID);
        call.enqueue(new Callback<CellarResponse>() {
            @Override
            public void onResponse(Call<CellarResponse> call, Response<CellarResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    Log.i("retrofit", "" + response.body().getWineCellars().get(0).getSensorList().get(0).getListOfMeasure().get(0).getDateInserted());
                    wineCellars.setValue(response.body().getWineCellars());
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
