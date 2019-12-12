package com.example.winecellarsensor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.winecellarsensor.R;

public class WeeklyFragment extends Fragment {
    public WeeklyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly, container, false);
    }
}
