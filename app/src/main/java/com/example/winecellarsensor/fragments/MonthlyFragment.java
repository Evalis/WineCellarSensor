package com.example.winecellarsensor.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Co2;
import com.example.winecellarsensor.model.Humidity;
import com.example.winecellarsensor.model.Measurements;
import com.example.winecellarsensor.model.Temperature;
import com.example.winecellarsensor.viewModel.CellarViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;

public class MonthlyFragment extends Fragment {

    private LineChart lineChartCo2;
    private LineChart lineChartTemperature;
    private LineChart lineChartHumidity;
    private CellarViewModel cellarViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_monthly, container, false);

        lineChartCo2 = rootView.findViewById(R.id.lineChartCo2Monthly);
        lineChartTemperature = rootView.findViewById(R.id.lineChartTemperatureMonthly);
        lineChartHumidity = rootView.findViewById(R.id.lineChartHumidityMonthly);

        cellarViewModel = ViewModelProviders.of(this).get(CellarViewModel.class);
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String id = prefs.getString("cellarID", null);
        cellarViewModel.getAllMonthlyMeasurements("basement",id);
        cellarViewModel.getMonthlyMeasurementsLiveData().observe(this.getActivity(), new Observer<Measurements>() {
            @Override
            public void onChanged(Measurements measurements) {
                ArrayList<Entry> co2EntriesMonthly = new ArrayList<>();
                ArrayList<Entry> temperatureEntriesMonthly = new ArrayList<>();
                ArrayList<Entry> humidityEntriesMonthly = new ArrayList<>();

                for (Co2 co2:measurements.getCo2List()) {
                    co2EntriesMonthly.add(new Entry(co2.getDate().getTime(),co2.getValue().floatValue()));
                    Log.i("Adika", co2.getDate().toString() +", "+ co2.getDate().getTime());
                }



                for (Temperature temperature:measurements.getTemperatureList()) {
                    temperatureEntriesMonthly.add(new Entry(temperature.getDate().getTime(),temperature.getValue().floatValue()));
                }

                for (Humidity humidity:measurements.getHumidityList()) {
                    humidityEntriesMonthly.add(new Entry(humidity.getDate().getTime(),humidity.getValue().floatValue()));
                }
                Collections.sort(co2EntriesMonthly, new Comparator<Entry>() {
                    @Override
                    public int compare(Entry o1, Entry o2) {
                        return Float.compare(o1.getX(),o2.getX());
                    }
                });
                Collections.sort(temperatureEntriesMonthly, new Comparator<Entry>() {
                    @Override
                    public int compare(Entry o1, Entry o2) {
                        return Float.compare(o1.getX(),o2.getX());
                    }
                });
                Collections.sort(humidityEntriesMonthly, new Comparator<Entry>() {
                    @Override
                    public int compare(Entry o1, Entry o2) {
                        return Float.compare(o1.getX(),o2.getX());
                    }
                });



        createLineChartCo2(lineChartCo2,co2EntriesMonthly,co2EntriesMonthly.get(co2EntriesMonthly.size()-1).getX(),co2EntriesMonthly.get(0).getX());
        createLineChartTemperature(lineChartTemperature,temperatureEntriesMonthly,temperatureEntriesMonthly.get(temperatureEntriesMonthly.size()-1).getX(),temperatureEntriesMonthly.get(0).getX());
        createLineChartHumidity(lineChartHumidity,humidityEntriesMonthly,humidityEntriesMonthly.get(humidityEntriesMonthly.size()-1).getX(),humidityEntriesMonthly.get(0).getX());
            }
        });
        return rootView;
    }

    private void createLineChartCo2(LineChart lineChartCo2, ArrayList<Entry> valuesCo2, float max, float min) {

        Description descriptionCo2 = new Description();
        descriptionCo2.setText("Co2");
        // descriptionCo2.setYOffset(20f);
        descriptionCo2.setTextSize(20);
        descriptionCo2.setTextColor(Color.WHITE);

        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setTextColor(Color.WHITE);

        LimitLine ll1 = new LimitLine(1000f, "MAX");
        ll1.setLineWidth(2f);
        ll1.setLineColor(Color.WHITE);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTextColor(Color.WHITE);

        LimitLine ll2 = new LimitLine(250f, "MIN");
        ll2.setLineWidth(2f);
        ll2.setLineColor(Color.WHITE);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTextColor(Color.WHITE);

        XAxis xAxis = lineChartCo2.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(max);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisMinimum(min);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setLabelCount(valuesCo2.size());
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setSpaceMin(2f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new MyValueFormatter());

        YAxis leftAxis = lineChartCo2.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(125f);
        leftAxis.setAxisMinimum(14f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChartCo2.getAxisRight().setEnabled(false);

        LineDataSet lineDataSetCo2 = new LineDataSet(valuesCo2, "CO2");
        lineDataSetCo2.setColor(Color.WHITE);
        lineDataSetCo2.enableDashedLine(10f, 5f, 0f);
        lineDataSetCo2.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSetCo2.setDrawCircleHole(false);
        lineDataSetCo2.setLineWidth(1f);
        lineDataSetCo2.setDrawCircles(true);
        lineDataSetCo2.setDrawCircleHole(false);
        lineDataSetCo2.setCircleColor(Color.WHITE);
        lineDataSetCo2.setCircleRadius(3f);
        lineDataSetCo2.setValueTextSize(0f);
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
        lineChartCo2.setGridBackgroundColor(Color.BLACK);
        lineChartCo2.setBackgroundColor(Color.TRANSPARENT);
        lineChartCo2.setTouchEnabled(false);
        lineChartCo2.setPinchZoom(true);
        lineChartCo2.getData().notifyDataChanged();

        Legend legendCo2 = lineChartCo2.getLegend();
        legendCo2.setEnabled(false);
        legendCo2.setTextColor(Color.WHITE);
        legendCo2.setForm(Legend.LegendForm.EMPTY);
        legendCo2.setTextSize(10f);
        legendCo2.setFormSize(10f);
        legendCo2.setXEntrySpace(5f);
        legendCo2.setFormToTextSpace(10f);
    }

    private void createLineChartTemperature(LineChart lineChartTemperature, ArrayList<Entry> valuesTemperature, float max, float min){

        Description descriptionTemperature = new Description();
        descriptionTemperature.setText("Temperature");
        descriptionTemperature.setTextSize(20);
        descriptionTemperature.setTextColor(Color.WHITE);

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

        XAxis xAxis = lineChartTemperature.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(max);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisMinimum(min);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setLabelCount(valuesTemperature.size(), true);
        xAxis.setValueFormatter(new MyValueFormatter());

        YAxis leftAxis = lineChartTemperature.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(50f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChartTemperature.getAxisRight().setEnabled(false);

        LineDataSet lineDataSetTemperature = new LineDataSet(valuesTemperature,"Temperature");
        lineDataSetTemperature.setColor(Color.WHITE);
        lineDataSetTemperature.enableDashedLine(10f, 5f, 0f);
        lineDataSetTemperature.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSetTemperature.setDrawCircleHole(false);
        lineDataSetTemperature.setLineWidth(1f);
        lineDataSetTemperature.setDrawCircles(true);
        lineDataSetTemperature.setDrawCircleHole(false);
        lineDataSetTemperature.setCircleColor(Color.WHITE);
        lineDataSetTemperature.setCircleRadius(3f);
        lineDataSetTemperature.setValueTextSize(0f);
        lineDataSetTemperature.setDrawFilled(true);
        lineDataSetTemperature.setFillColor(Color.WHITE);
        lineDataSetTemperature.setFormLineWidth(1f);
        lineDataSetTemperature.setValueTextColor(Color.WHITE);
        lineDataSetTemperature.setDrawIcons(false);
        lineDataSetTemperature.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));

        ArrayList<ILineDataSet> dataSetsTemperature = new ArrayList<>();
        dataSetsTemperature.add(lineDataSetTemperature);
        LineData dataTemperature = new LineData(dataSetsTemperature);
        lineChartTemperature.setData(dataTemperature);
        lineChartTemperature.invalidate();
        lineChartTemperature.setDescription(descriptionTemperature);
        lineChartTemperature.setNoDataText("No data is available.");
        lineChartTemperature.setNoDataTextColor(Color.WHITE);
        lineChartTemperature.setDrawGridBackground(false);
        lineChartTemperature.setGridBackgroundColor(Color.BLACK);
        lineChartTemperature.setBackgroundColor(Color.TRANSPARENT);
        lineChartTemperature.setTouchEnabled(true);
        lineChartTemperature.setPinchZoom(true);
        lineChartTemperature.getData().notifyDataChanged();

        Legend legendTemperature = lineChartTemperature.getLegend();
        legendTemperature.setEnabled(false);
        legendTemperature.setTextColor(Color.WHITE);
        legendTemperature.setForm(Legend.LegendForm.EMPTY);
        legendTemperature.setTextSize(10f);
        legendTemperature.setFormSize(10f);
        legendTemperature.setXEntrySpace(5f);
        legendTemperature.setFormToTextSpace(10f);
    }

    private void createLineChartHumidity(LineChart lineChartHumidity,ArrayList<Entry> valuesHumidity, float max, float min){
        Description descriptionHumidity = new Description();
        descriptionHumidity.setText("Humidity");
        descriptionHumidity.setTextSize(20);
        descriptionHumidity.setTextColor(Color.WHITE);

        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setTextColor(Color.WHITE);

        LimitLine ll1 = new LimitLine(60f, "MAX");
        ll1.setLineWidth(2f);
        ll1.setLineColor(Color.WHITE);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTextColor(Color.WHITE);

        LimitLine ll2 = new LimitLine(30f, "MIN");
        ll2.setLineWidth(2f);
        ll2.setLineColor(Color.WHITE);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTextColor(Color.WHITE);

        XAxis xAxis = lineChartHumidity.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(max);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisMinimum(min);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setLabelCount(valuesHumidity.size(), true);
        xAxis.setValueFormatter(new MyValueFormatter());

        YAxis leftAxis = lineChartHumidity.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChartHumidity.getAxisRight().setEnabled(false);

        LineDataSet lineDataSetHumidity = new LineDataSet(valuesHumidity,"Humidty");
        lineDataSetHumidity.setColor(Color.WHITE);
        lineDataSetHumidity.enableDashedLine(10f, 5f, 0f);
        lineDataSetHumidity.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSetHumidity.setDrawCircleHole(false);
        lineDataSetHumidity.setLineWidth(1f);
        lineDataSetHumidity.setDrawCircles(true);
        lineDataSetHumidity.setDrawCircleHole(false);
        lineDataSetHumidity.setCircleColor(Color.WHITE);
        lineDataSetHumidity.setCircleRadius(3f);
        lineDataSetHumidity.setValueTextSize(0f);
        lineDataSetHumidity.setDrawFilled(true);
        lineDataSetHumidity.setFillColor(Color.WHITE);
        lineDataSetHumidity.setFormLineWidth(1f);
        lineDataSetHumidity.setValueTextColor(Color.WHITE);
        lineDataSetHumidity.setDrawIcons(false);
        lineDataSetHumidity.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));

        ArrayList<ILineDataSet> dataSetsHumidity = new ArrayList<>();
        dataSetsHumidity.add(lineDataSetHumidity);
        LineData dataHumidity = new LineData(dataSetsHumidity);
        lineChartHumidity.setData(dataHumidity);
        lineChartHumidity.invalidate();
        lineChartHumidity.setDescription(descriptionHumidity);
        lineChartHumidity.setNoDataText("No data is available.");
        lineChartHumidity.setNoDataTextColor(Color.WHITE);
        lineChartHumidity.setDrawGridBackground(false);
        lineChartHumidity.setGridBackgroundColor(Color.BLACK);
        lineChartHumidity.setBackgroundColor(Color.TRANSPARENT);
        lineChartHumidity.setTouchEnabled(true);
        lineChartHumidity.setPinchZoom(true);
        lineChartHumidity.getData().notifyDataChanged();

        Legend legendHumidity = lineChartHumidity.getLegend();
        legendHumidity.setEnabled(false);
        legendHumidity.setTextColor(Color.WHITE);
        legendHumidity.setForm(Legend.LegendForm.EMPTY);
        legendHumidity.setTextSize(10f);
        legendHumidity.setFormSize(10f);
        legendHumidity.setXEntrySpace(5f);
        legendHumidity.setFormToTextSpace(10f);
    }

    private class MyValueFormatter implements IAxisValueFormatter {
        private SimpleDateFormat mFormat;
        public MyValueFormatter() {
            mFormat = new SimpleDateFormat("MM/dd");
            mFormat.setTimeZone(TimeZone.getTimeZone("CET"));

        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.i("Adika", new Date((long)value)+", " + value);
            return mFormat.format(new Date((long)value));

        }
    }
}
