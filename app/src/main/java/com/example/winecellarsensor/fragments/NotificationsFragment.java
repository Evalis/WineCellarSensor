package com.example.winecellarsensor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Warning;
import com.example.winecellarsensor.viewModel.CellarViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class NotificationsFragment extends Fragment {

    private CellarViewModel cellarViewModel;
    Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        cellarViewModel.getWarnings().observe(this, new Observer<List<Warning>>() {
            @Override
            public void onChanged(List<Warning> warnings) {
                //update recycle view

                Toast.makeText(c, "onChanges", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
