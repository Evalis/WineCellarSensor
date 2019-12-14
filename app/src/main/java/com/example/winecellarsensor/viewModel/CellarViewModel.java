package com.example.winecellarsensor.viewModel;

import android.app.Application;

import com.example.winecellarsensor.model.Room;
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

    public LiveData<List<Room>> getRooms() {

        return repository.getAllRoom();
    }

    public void updateCellar(String cellarID) {

        repository.updateCellar(cellarID);
    }
}
