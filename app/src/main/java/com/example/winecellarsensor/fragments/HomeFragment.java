package com.example.winecellarsensor.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.viewModel.CellarViewModel;
import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.view.MainActivity;
import com.example.winecellarsensor.adapters.RoomAdapter;
import com.example.winecellarsensor.view.RoomDetail;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment implements RoomAdapter.OnListItemClickListener {
    RecyclerView roomRecyclerView;
    RoomAdapter roomAdapter;
    Context c;
    Dialog dialog;
    ImageView closeDialog;

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


        dialog = new Dialog(c);
        dialog.show();

        cellarViewModel.getRooms().observe(this.getActivity(), new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                roomAdapter.setRooms(rooms);
             outOfBoundValues(rooms);
            }
        });

        return rootView;
    }


    @Override
    public void onListItemClick(Room room) {
        Intent intent = new Intent(c, RoomDetail.class);
        intent.putExtra("Room", room);
        startActivity(intent);
    }

    private void outOfBoundValues(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {

            SharedPreferences prefs = c.getSharedPreferences("MyPreferences", MODE_PRIVATE);
            double co2Min = Double.parseDouble(prefs.getString("Co2MinRange" + rooms.get(i).getRoomName(), null));
            double co2Max = Double.parseDouble(prefs.getString("Co2MaxRange" + rooms.get(i).getRoomName(), null));
            double tempMin = Double.parseDouble(prefs.getString("TempMinRange" + rooms.get(i).getRoomName(), null));
            double tempMax = Double.parseDouble(prefs.getString("TempMaxRange" + rooms.get(i).getRoomName(), null));
            double humMin = Double.parseDouble(prefs.getString("HumMinRange" + rooms.get(i).getRoomName(), null));
            double humMax = Double.parseDouble(prefs.getString("HumMaxRange" + rooms.get(i).getRoomName(), null));

            double currentCo2 = rooms.get(i).getCo2().getValue();
            double currentTemp = rooms.get(i).getTemperature().getValue();
            double currentHum = rooms.get(i).getHumidity().getValue();

            if(currentCo2 <= co2Min || currentCo2>= co2Max)
            {
                Log.i("Dialog", "dialog not working ");
                showDialog();
            }

            if(currentTemp <= tempMin || currentTemp>= tempMax)
            {
                showDialog();
            }

            if(currentHum <= humMin || currentHum >= humMax)
            {

            }
            showDialog();
        }
    }

    private void showDialog()
    {
        dialog.setContentView(R.layout.dialog_warning);
        closeDialog = dialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}