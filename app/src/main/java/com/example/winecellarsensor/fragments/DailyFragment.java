package com.example.winecellarsensor.fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import com.example.winecellarsensor.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DailyFragment extends Fragment {

    private LineChart lineChartCo2;
    private LineChart lineChartTemperature;
    private LineChart lineChartHumidity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_daily, container, false);

        lineChartCo2 = rootView.findViewById(R.id.lineChartCo2Daily);
        lineChartTemperature = rootView.findViewById(R.id.lineChartTemperatureDaily);
        lineChartHumidity = rootView.findViewById(R.id.lineChartHumidityDaily);

        Description descriptionCo2 = new Description();
        descriptionCo2.setText("");
        descriptionCo2.setTextSize(15);
        descriptionCo2.setTextColor(Color.WHITE);

        Description descriptionTemperature = new Description();
        descriptionTemperature.setText("");
        descriptionTemperature.setTextSize(15);
        descriptionTemperature.setTextColor(Color.DKGRAY);

        Description descriptionHumidity = new Description();
        descriptionHumidity.setText("");
        descriptionHumidity.setTextSize(15);
        descriptionHumidity.setTextColor(Color.DKGRAY);

        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setTextColor(Color.WHITE);

        LimitLine ll1 = new LimitLine(20f, "MAX");
        ll1.setLineWidth(2f);
        ll1.setLineColor(Color.WHITE);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTextColor(Color.WHITE);

        LimitLine ll2 = new LimitLine(-10f, "MIN");
        ll2.setLineWidth(2f);
        ll2.setLineColor(Color.WHITE);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTextColor(Color.WHITE);

        XAxis xAxis = lineChartCo2.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(23f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawLimitLinesBehindData(true);

        YAxis leftAxis = lineChartCo2.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(25f);
        leftAxis.setAxisMinimum(-20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        lineChartCo2.getAxisRight().setEnabled(false);

        LineDataSet lineDataSetCo2 = new LineDataSet(getValuesCo2(),"CO2");
        lineDataSetCo2.setColor(Color.WHITE);
        lineDataSetCo2.enableDashedLine(10f, 5f, 0f);
        lineDataSetCo2.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSetCo2.setDrawCircleHole(false);
        lineDataSetCo2.setLineWidth(1f);
        lineDataSetCo2.setDrawCircles(true);
        lineDataSetCo2.setDrawCircleHole(false);
        lineDataSetCo2.setCircleColor(Color.WHITE);
        lineDataSetCo2.setCircleRadius(3f);
        lineDataSetCo2.setValueTextSize(9f);
        lineDataSetCo2.setDrawFilled(true);
        lineDataSetCo2.setFillColor(Color.WHITE);
        lineDataSetCo2.setFormLineWidth(1f);
        lineDataSetCo2.setValueTextColor(Color.WHITE);
        lineDataSetCo2.setDrawIcons(false);
        lineDataSetCo2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));

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
        lineChartCo2.setTouchEnabled(true);
        lineChartCo2.setPinchZoom(true);
        lineChartCo2.getData().notifyDataChanged();


        Legend legendCo2 = lineChartCo2.getLegend();
        legendCo2.setEnabled(true);
        legendCo2.setTextColor(Color.WHITE);
        legendCo2.setTextSize(10);
        legendCo2.setForm(Legend.LegendForm.EMPTY);
        legendCo2.setTextSize(10f);
        legendCo2.setFormSize(10f);
        legendCo2.setXEntrySpace(5f);
        legendCo2.setFormToTextSpace(10f);

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
        entries.add(new Entry(6, -20));
        entries.add(new Entry(7, -12));
        entries.add(new Entry(8, 2));
        entries.add(new Entry(9, 9));
        entries.add(new Entry(10, 6));
        entries.add(new Entry(11, 18));
        entries.add(new Entry(12, 15));
        entries.add(new Entry(13, 5));
        entries.add(new Entry(14, 8));
        entries.add(new Entry(15, 4));
        entries.add(new Entry(16, 17));
        entries.add(new Entry(17, -3));
        entries.add(new Entry(18, 20));
        entries.add(new Entry(19, 12));
        entries.add(new Entry(20, 2));
        entries.add(new Entry(21, 3));
        entries.add(new Entry(22, 6));
        entries.add(new Entry(23, 20));

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
