package com.example.winecellarsensor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.winecellarsensor.R;

public class MonthlyFragment extends Fragment {
    public MonthlyFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_monthly, container, false);
        return rootView;
    }
}
