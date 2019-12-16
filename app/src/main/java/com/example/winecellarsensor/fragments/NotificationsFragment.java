package com.example.winecellarsensor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.adapters.WarningAdapter;
import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.model.Warning;
import com.example.winecellarsensor.viewModel.CellarViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationsFragment extends Fragment {


    RecyclerView warningsList;
    WarningAdapter warningsAdapter;
    private CellarViewModel cellarViewModel;
    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        warningsList = rootView.findViewById(R.id.rv_warning);
        warningsList.hasFixedSize();
        warningsList.setLayoutManager(new LinearLayoutManager(c));

        warningsAdapter = new WarningAdapter();
        warningsList.setAdapter(warningsAdapter);

        cellarViewModel.getWarnings().observe(this.getActivity(), new Observer<List<Warning>>() {
                    @Override
                    public void onChanged(List<Warning> warnings) {
                        warningsAdapter.setWarnings(warnings);
                    }
                });

        return rootView;
    }
}
