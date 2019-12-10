package com.example.winecellarsensor.ViewModel;

import android.app.Application;

import com.example.winecellarsensor.model.WineCellar;
import com.example.winecellarsensor.repositories.CellarRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CellarViewModel extends AndroidViewModel {

    CellarRepository repository;

    public CellarViewModel(@NonNull Application application) {
        super(application);
        repository = CellarRepository.getInstance(application);
    }

    public LiveData<List<WineCellar>> getCellar() {

        return repository.getCellar();
    }

    public void updateCellar(String cellarID) {

        repository.updateCellar(cellarID);
    }
}
