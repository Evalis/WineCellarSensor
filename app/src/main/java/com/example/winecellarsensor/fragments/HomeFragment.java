package com.example.winecellarsensor.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.ViewModel.CellarViewModel;
import com.example.winecellarsensor.model.WineCellar;
import com.example.winecellarsensor.view.MainActivity;
import com.example.winecellarsensor.view.RoomAdapter;
import com.example.winecellarsensor.view.RoomDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class HomeFragment extends Fragment implements RoomAdapter.OnListItemClickListener {
    RecyclerView roomRecyclerView;
    RoomAdapter roomAdapter;
    Context c;

    private CellarViewModel cellarViewModel;


    public HomeFragment(MainActivity mainActivity, CellarViewModel cellarViewModel) {
        c = mainActivity;
        this.cellarViewModel = cellarViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        roomRecyclerView = rootView.findViewById(R.id.rv);
        roomRecyclerView.hasFixedSize();
        roomRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        roomAdapter = new RoomAdapter(this);
        roomRecyclerView.setAdapter(roomAdapter);


        cellarViewModel.getCellar().observe(this.getActivity(), new Observer<List<WineCellar>>() {
            @Override
            public void onChanged(List<WineCellar> cellars) {
                roomAdapter.setCellar(cellars.get(0));
            }
        });

        return rootView;
    }

    public void onListItemClick(String room) {
        Intent intent = new Intent(c, RoomDetail.class);
        intent.putExtra("Room", room);
        startActivity(intent);
    }
}