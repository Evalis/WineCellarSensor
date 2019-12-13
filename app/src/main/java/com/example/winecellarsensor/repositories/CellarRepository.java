package com.example.winecellarsensor.repositories;

import android.app.Application;
import android.util.Log;
import com.example.winecellarsensor.apis.CellarAPI;
import com.example.winecellarsensor.apis.CellarResponse;
import com.example.winecellarsensor.apis.CellarServiceGenerator;
import com.example.winecellarsensor.model.Room;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellarRepository {

    private static CellarRepository instance;
    private MutableLiveData<List<Room>> rooms;

    private CellarRepository(Application application)
    {
        rooms = new MutableLiveData<List<Room>>();

    }

    public static synchronized CellarRepository getInstance( Application application) {
        if (instance == null) {
            instance = new CellarRepository(application);
        }
        return instance;
    }

    public LiveData<List<Room>> getAllRoom(){
        return rooms;
    }

    public void updateCellar(String cellarID) {
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<CellarResponse> call = cellarAPI.getAllRooms(cellarID);
        call.enqueue(new Callback<CellarResponse>() {
            @Override
            public void onResponse(Call<CellarResponse> call, Response<CellarResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    rooms.setValue(response.body().getRooms());
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
