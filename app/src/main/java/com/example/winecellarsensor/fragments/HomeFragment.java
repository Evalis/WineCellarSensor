package com.example.winecellarsensor.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Warning;

import com.example.winecellarsensor.model.Co2;
import com.example.winecellarsensor.model.Humidity;
import com.example.winecellarsensor.model.Temperature;
import com.example.winecellarsensor.model.Warning;
import com.example.winecellarsensor.viewModel.CellarViewModel;
import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.view.MainActivity;
import com.example.winecellarsensor.adapters.RoomAdapter;
import com.example.winecellarsensor.view.RoomDetail;

import java.util.Date;
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
    Button dialogOk;
    TextView roomName, dateTime, sensor, max, min, actual;

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
            double co2Min = Double.parseDouble(prefs.getString("Co2MinRange" + rooms.get(i).getRoomName(), "19"));
            double co2Max = Double.parseDouble(prefs.getString("Co2MaxRange" + rooms.get(i).getRoomName(), "19"));
            double tempMin = Double.parseDouble(prefs.getString("TempMinRange" + rooms.get(i).getRoomName(), "19"));
            double tempMax = Double.parseDouble(prefs.getString("TempMaxRange" + rooms.get(i).getRoomName(), "19"));
            double humMin = Double.parseDouble(prefs.getString("HumMinRange" + rooms.get(i).getRoomName(), "19"));
            double humMax = Double.parseDouble(prefs.getString("HumMaxRange" + rooms.get(i).getRoomName(), "19"));

            double currentCo2 = rooms.get(i).getCo2().getValue();
            double currentTemp = rooms.get(i).getTemperature().getValue();
            double currentHum = rooms.get(i).getHumidity().getValue();

            if(currentCo2 <= co2Min || currentCo2>= co2Max)
            {
                Warning warning = new Warning(rooms.get(i).getRoomName(), Co2.TYPE, rooms.get(i).getCo2().getDate(),  co2Min, co2Max, currentCo2);
                showDialog(warning);
                saveToLogWarning(warning);
            }

            if(currentTemp <= tempMin || currentTemp>= tempMax)
            {
                Warning warning = new Warning(rooms.get(i).getRoomName(), Temperature.TYPE, rooms.get(i).getTemperature().getDate(),  tempMin, tempMax, currentTemp);
                showDialog(warning);
                saveToLogWarning(warning);            }

            if(currentHum <= humMin || currentHum >= humMax)
            {
                Warning warning = new Warning(rooms.get(i).getRoomName(), Humidity.TYPE, rooms.get(i).getHumidity().getDate(),  humMin, humMax, currentHum);
                showDialog(warning);
                saveToLogWarning(warning);
            }

        }
    }

    private void showDialog(Warning warning)
    {
        dialog.setContentView(R.layout.dialog_warning);
        closeDialog = dialog.findViewById(R.id.closeDialog);

        roomName = dialog.findViewById(R.id.dialog_roomName);
        roomName.setText(warning.getRoomName());

        sensor = dialog.findViewById(R.id.dialog_sensorName);
        sensor.setText(warning.getSensorName());

        dateTime = dialog.findViewById(R.id.dialog_dateTime);
        dateTime.setText(warning.getDate().toString());

        max = dialog.findViewById(R.id.dialog_max);
        max.setText(warning.getMaxValue()+"");

        min = dialog.findViewById(R.id.dialog_min);
        min.setText(warning.getMinValue()+"");

        actual = dialog.findViewById(R.id.dialog_actual);
        actual.setText(warning.getActualValue()+"");


        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialogOk = dialog.findViewById(R.id.btn_OK);
        dialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

    public void saveToLogWarning(Warning warning){
       cellarViewModel.insert(warning);
    }
}