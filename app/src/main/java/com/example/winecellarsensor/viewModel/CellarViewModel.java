package com.example.winecellarsensor.viewModel;

import android.app.Application;

import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.model.Warning;
import com.example.winecellarsensor.repositories.CellarRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CellarViewModel extends AndroidViewModel {

    CellarRepository repository;
    private LiveData<List<Warning>> warnings;

    public CellarViewModel(@NonNull Application application) {
        super(application);
        repository = CellarRepository.getInstance(application);
        warnings = repository.getAllWarnings();
    }

    public void insert(Warning warning)
    {
        repository.insert(warning);
    }

    public void update(Warning warning)
    {
        repository.update(warning);
    }

    public void delete(Warning warning)
    {
        repository.delete(warning);
    }

    public void deleteAllWanings()
    {
        repository.deleteAllWarnings();
    }

    public LiveData<List<Warning>> getWarnings()
    {
        return warnings;
    }

    public LiveData<List<Room>> getRooms() {

        return repository.getAllRoom();
    }

    public void updateCellar(String cellarID) {

        repository.updateCellar(cellarID);
    }
}
