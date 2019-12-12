package com.example.winecellarsensor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.winecellarsensor.R;

public class DailyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView  = inflater.inflate(R.layout.fragment_daily, container, false);
        Button buttonDailyFragment = rootView.findViewById(R.id.buttonDaily);
        buttonDailyFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Daily fragment", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
