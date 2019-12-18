package com.example.winecellarsensor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Room;
import com.example.winecellarsensor.viewModel.CellarViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

public class RoomDetail extends AppCompatActivity {

    private CellarViewModel cellarViewModel;
    Room room;
    private TextView roomName, humidity, co2, temperature;
    private Button buttonStatistics;
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                room = null;
            } else {
                room = (Room) extras.getSerializable("Room");
            }
        } else {
            room = (Room) savedInstanceState.getSerializable("Room");
        }

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);

       roomName = findViewById(R.id.roomName);
       roomName.setText(room.getRoomName());

        temperature = findViewById(R.id.temperature);
        temperature.setText(room.getTemperature().getValue().toString());

        humidity = findViewById(R.id.humidity);
        humidity.setText(room.getHumidity().getValue().toString());
        co2 = findViewById(R.id.co2);
        co2.setText(room.getCo2().getValue().toString());



        buttonSettings = findViewById(R.id.btn_Settings);
        buttonStatistics = findViewById(R.id.btn_Statistics);

        buttonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStatistics(view);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettings(view);
            }
        });

    }

    public void showStatistics(View view) {
        Intent i = new Intent(RoomDetail.this, StatisticsActivity.class);
        i.putExtra("RoomName", room.getRoomName());
        startActivity(i);
    }

    public void showSettings(View view) {
        Intent i = new Intent(RoomDetail.this, SettingsActivity.class);
        i.putExtra("RoomName", room.getRoomName());
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
