package com.example.winecellarsensor.ViewModel;

import android.app.Application;
import com.example.winecellarsensor.model.Cellar;
import com.example.winecellarsensor.repositories.CellarRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CellarViewModel extends AndroidViewModel {

    CellarRepository repository;

    public CellarViewModel(@NonNull Application application) {
        super(application);
        repository = CellarRepository.getInstance(application);
    }

    public LiveData<Cellar> getCellar() {

        return repository.getCellar();
    }

    public void updateCellar() {

        repository.updateCellar();
    }
}
