package com.example.winecellarsensor.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.winecellarsensor.R;

public class SettingsActivity extends AppCompatActivity {

    private EditText minValueTemp, maxValueTemp, minValueCo2, maxValueCo2, minValueHum, maxValueHum;
    private Button btn_saveSettings;
    private String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                roomName = null;
            } else {
                roomName = (String) extras.getSerializable("RoomName");
            }
        } else {
            roomName = (String)savedInstanceState.getSerializable("RoomName");
        }

        Toolbar toolbar = findViewById(R.id.toolSettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        minValueCo2 = findViewById(R.id.min_value_co2);
        String co2Min = prefs.getString("Co2MinRange"+ roomName, null);
        minValueCo2.setText(co2Min);

        maxValueCo2 = findViewById(R.id.max_value_co2);
        String co2Max = prefs.getString("Co2MinRange"+ roomName, null);
        maxValueCo2.setText(co2Max);

        minValueTemp = findViewById(R.id.min_value_temp);
        String tempMin = prefs.getString("TempMinRange"+ roomName, null);
        minValueTemp.setText(tempMin);

        maxValueTemp = findViewById(R.id.max_value_temp);
        String tempMax = prefs.getString("TempMaxRange"+ roomName, null);
        maxValueTemp.setText(tempMax);

        minValueHum = findViewById(R.id.min_value_hum);
        String humMin = prefs.getString("HumMinRange"+ roomName, null);
        minValueHum.setText(humMin);

        maxValueHum = findViewById(R.id.max_value_hum);
        String humMax = prefs.getString("HumMaxRange"+ roomName, null);
        maxValueHum.setText(humMax);

        btn_saveSettings = findViewById(R.id.btn_saveSettings);
        btn_saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
                finish();

            }
        });
    }

    public void saveSettings ()
    {
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Co2MinRange"+ roomName, minValueCo2.getText().toString());
        editor.putString("Co2MaxRange"+ roomName, maxValueCo2.getText().toString());
        editor.putString("HumMinRange"+ roomName, minValueHum.getText().toString());
        editor.putString("HumMaxRange"+ roomName, maxValueHum.getText().toString());
        editor.putString("TempMinRange"+ roomName, minValueTemp.getText().toString());
        editor.putString("TempMaxRange"+ roomName, maxValueTemp.getText().toString());
        editor.apply();
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
