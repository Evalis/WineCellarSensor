package com.example.winecellarsensor.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.winecellarsensor.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class WeeklyFragment extends Fragment {

    private LineChart lineChartCo2;
    private LineChart lineChartTemperature;
    private LineChart lineChartHumidity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weekly, container, false);

        lineChartCo2 = rootView.findViewById(R.id.lineChartCo2Weekly);
        lineChartTemperature = rootView.findViewById(R.id.lineChartTemperatureWeekly);
        lineChartHumidity = rootView.findViewById(R.id.lineChartHumidityWeekly);

        Description descriptionCo2 = new Description();
        descriptionCo2.setText("");
        descriptionCo2.setTextSize(15);
        descriptionCo2.setTextColor(Color.DKGRAY);

        Description descriptionTemperature = new Description();
        descriptionTemperature.setText("");
        descriptionTemperature.setTextSize(15);
        descriptionTemperature.setTextColor(Color.DKGRAY);

        Description descriptionHumidity = new Description();
        descriptionHumidity.setText("");
        descriptionHumidity.setTextSize(15);
        descriptionHumidity.setTextColor(Color.DKGRAY);

        LineDataSet lineDataSetCo2 = new LineDataSet(getValuesCo2(),"CO2");
        lineDataSetCo2.setColor(Color.RED);
        lineDataSetCo2.setLineWidth(4);
        lineDataSetCo2.setDrawCircles(true);
        lineDataSetCo2.setDrawCircleHole(false);
        lineDataSetCo2.setCircleColor(Color.RED);
        lineDataSetCo2.setCircleRadius(5);
        lineDataSetCo2.setValueTextColor(Color.RED);
        ArrayList<ILineDataSet> dataSetsCo2 = new ArrayList<>();
        dataSetsCo2.add(lineDataSetCo2);
        LineData dataCo2 = new LineData(dataSetsCo2);
        lineChartCo2.setData(dataCo2);
        lineChartCo2.invalidate();
        lineChartCo2.setDescription(descriptionCo2);
        lineChartCo2.setNoDataText("No data is available.");
        lineChartCo2.setNoDataTextColor(Color.WHITE);
        lineChartCo2.setDrawGridBackground(false);
        lineChartCo2.setBackgroundColor(Color.TRANSPARENT);
        lineChartCo2.setBorderColor(Color.WHITE);

        Legend legendCo2 = lineChartCo2.getLegend();
        legendCo2.setEnabled(true);
        legendCo2.setTextColor(Color.WHITE);
        legendCo2.setTextSize(10);
        legendCo2.setForm(Legend.LegendForm.CIRCLE);
        legendCo2.setFormSize(10);
        legendCo2.setXEntrySpace(5);
        legendCo2.setFormToTextSpace(10);

       /* LegendEntry[] entriesCo2 = new LegendEntry[7];

        for(int i= 0;i<=entriesCo2.length;i++){
            LegendEntry entryCo2 = new LegendEntry();
            entryCo2.formColor = Color.GREEN;
            entriesCo2[i] = entryCo2;
        }
        legendCo2.setCustom(entriesCo2);*/

        LineDataSet lineDataSetTemperature = new LineDataSet(getValuesTemperature(),"Temperature");
        ArrayList<ILineDataSet> dataSetsTemperature = new ArrayList<>();
        dataSetsTemperature.add(lineDataSetTemperature);
        LineData dataTemperature = new LineData(dataSetsTemperature);
        lineChartTemperature.setData(dataTemperature);
        lineChartTemperature.invalidate();
        lineChartTemperature.setDescription(descriptionTemperature);
        lineChartTemperature.setNoDataText("No data is available.");
        lineChartTemperature.setNoDataTextColor(Color.WHITE);
        // lineChartTemperature.setDrawGridBackground(true);
        lineChartTemperature.setBackgroundColor(Color.WHITE);

        LineDataSet lineDataSetHumidity = new LineDataSet(getValuesHumidity(),"Humidity");
        ArrayList<ILineDataSet> dataSetsHumidity = new ArrayList<>();
        dataSetsHumidity.add(lineDataSetHumidity);
        LineData dataHumidity = new LineData(dataSetsHumidity);
        lineChartHumidity.setData(dataHumidity);
        lineChartHumidity.invalidate();
        lineChartHumidity.setDescription(descriptionHumidity);
        lineChartHumidity.setNoDataText("No data is available.");
        lineChartHumidity.setNoDataTextColor(Color.WHITE);
        lineChartHumidity.setBackgroundColor(Color.WHITE);

        return rootView;
    }

    private ArrayList<Entry> getValuesCo2(){

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 12));
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 9));
        entries.add(new Entry(3, 4));
        entries.add(new Entry(4, 20));
        entries.add(new Entry(5, 4));
        entries.add(new Entry(6, 20));

        return entries;
    }

    private ArrayList<Entry> getValuesTemperature(){

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 12));
        entries.add(new Entry(1, 18));
        entries.add(new Entry(2, -15));
        entries.add(new Entry(3, 10));
        entries.add(new Entry(4, 20));
        entries.add(new Entry(5, 11));
        entries.add(new Entry(6, 24));

        return entries;
    }

    private ArrayList<Entry> getValuesHumidity(){

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 100));
        entries.add(new Entry(1, 300));
        entries.add(new Entry(2, 15));
        entries.add(new Entry(3, 1));
        entries.add(new Entry(4, 20));
        entries.add(new Entry(5, 2));
        entries.add(new Entry(6, 19));

        return entries;
    }
}
