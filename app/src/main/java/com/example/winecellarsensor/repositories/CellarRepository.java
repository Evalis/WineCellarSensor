package com.example.winecellarsensor.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.winecellarsensor.apis.CellarAPI;
import com.example.winecellarsensor.apis.CellarResponse;
import com.example.winecellarsensor.apis.CellarServiceGenerator;
import com.example.winecellarsensor.apis.MeasurementsResponse;
import com.example.winecellarsensor.database.WarningDao;
import com.example.winecellarsensor.database.WarningDatabase;
import com.example.winecellarsensor.model.Measurements;
import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.model.Warning;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellarRepository {

    private WarningDao warningDao;
    private LiveData<List<Warning>> warnings;
    private static CellarRepository instance;
    private MutableLiveData<List<Room>> rooms;
    private MutableLiveData<Measurements> weeklyMeasurements;
    private MutableLiveData<Measurements> monthlyMeasurements;
    private MutableLiveData<Measurements> dailyMeasurements;

    private CellarRepository(Application application)
    {
        rooms = new MutableLiveData<List<Room>>();
        WarningDatabase database = WarningDatabase.getInstance(application);
        warningDao = database.warningDao();
        warnings = warningDao.getAllWarnings();
        weeklyMeasurements = new MutableLiveData<Measurements>();
        monthlyMeasurements = new MutableLiveData<Measurements>();
        dailyMeasurements = new MutableLiveData<Measurements>();
    }


    public void insert(Warning warning)
    {
      new InsertWarningAsyncTask(warningDao).execute(warning);
    }


    public void update(Warning warning)
    {
        new UpdateWarningAsyncTask(warningDao).execute(warning);
    }


    public void delete(Warning warning)
    {
        new DeleteWarningAsyncTask(warningDao).execute(warning);
    }


    public void deleteAllWarnings()
    {
        new DeleteAllWarningAsyncTask(warningDao).execute();
    }

    public LiveData<List<Warning>> getAllWarnings()
    {
        return warnings;
    }

    private static class InsertWarningAsyncTask extends AsyncTask<Warning, Void, Void>{
        private WarningDao warningDao;

        private InsertWarningAsyncTask(WarningDao warningDao){
            this.warningDao = warningDao;
        }
        @Override
        protected Void doInBackground(Warning... warnings) {
            warningDao.insert(warnings[0]);
            return null;
        }
    }
    private static class UpdateWarningAsyncTask extends AsyncTask<Warning, Void, Void>{
        private WarningDao warningDao;

        private UpdateWarningAsyncTask(WarningDao warningDao){
            this.warningDao = warningDao;
        }
        @Override
        protected Void doInBackground(Warning... warnings) {
            warningDao.update(warnings[0]);
            return null;
        }
    }

    private static class DeleteWarningAsyncTask extends AsyncTask<Warning, Void, Void>{
        private WarningDao warningDao;

        private DeleteWarningAsyncTask(WarningDao warningDao){
            this.warningDao = warningDao;
        }
        @Override
        protected Void doInBackground(Warning... warnings) {
            warningDao.delete(warnings[0]);
            return null;
        }
    }

    private static class DeleteAllWarningAsyncTask extends AsyncTask<Void, Void, Void>{
        private WarningDao warningDao;

        private DeleteAllWarningAsyncTask(WarningDao warningDao){
            this.warningDao = warningDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            warningDao.deleteAllWarnings();
            return null;
        }
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

    public void getWeeklyMeasurements(String roomName, String cellarID){
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<MeasurementsResponse> call = cellarAPI.getAllWeeklyMeasurements(roomName, cellarID);
        call.enqueue(new Callback<MeasurementsResponse>() {
            @Override
            public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    weeklyMeasurements.setValue(response.body().getAllWeeklyMeasurements());
                    //Log.i("Luci", response.body().getAllWeeklyMeasurements()+"");
                    /*weeklyMeasurements.setCo2List(response.body().getAllWeeklyMeasurements().getCo2List());
                    weeklyMeasurements.setHumidityList(response.body().getAllWeeklyMeasurements().getHumidityList());
                    weeklyMeasurements.setRoomName(response.body().getAllWeeklyMeasurements().getRoomName());
                    weeklyMeasurements.setTemperatureList(response.body().getAllWeeklyMeasurements().getTemperatureList());
                    Log.d("Luci", weeklyMeasurements.getRoomName()+", "+ weeklyMeasurements.getTemperatureList().get(0).getValue()+", " + weeklyMeasurements.getTemperatureList().get(0).getDate() );*/
                }
            }

            @Override
            public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }

    public LiveData<Measurements> getWeeklyMeasurementsLiveData(){
        return weeklyMeasurements;
    }

    public void getMonthlyMeasurements(String roomName, String cellarID){
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<MeasurementsResponse> call = cellarAPI.getAllMonthlyMeasurements(roomName, cellarID);
        call.enqueue(new Callback<MeasurementsResponse>() {
            @Override
            public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    monthlyMeasurements.setValue(response.body().getAllMonthlyMeasurements());
                    //Log.i("Luci", response.body().getAllWeeklyMeasurements()+"");
                }
            }

            @Override
            public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }

    public LiveData<Measurements> getMonthlyMeasurementsLiveData(){
        return monthlyMeasurements;
    }

    public void getDailyMeasurements(String roomName, String cellarID){
        CellarAPI cellarAPI = CellarServiceGenerator.getCellarAPI();
        Call<MeasurementsResponse> call = cellarAPI.getAllDailyMeasurements(roomName, cellarID);
        call.enqueue(new Callback<MeasurementsResponse>() {
            @Override
            public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    dailyMeasurements.setValue(response.body().getAllDailyMeasurements());

                }
            }

            @Override
            public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }

    public LiveData<Measurements> getDailyMeasurementsLiveData(){
        return dailyMeasurements;
    }
}