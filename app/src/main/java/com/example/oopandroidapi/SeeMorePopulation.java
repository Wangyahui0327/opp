package com.example.oopandroidapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class SeeMorePopulation extends AppCompatActivity {
    private Toolbar toolbar4;
    private ImageButton imageButtonBack4;
    private TextView textViewStatsSource4, textViewScreen4;
    private ImageView imageViewStatsPopulation4;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more_population);

        Toolbar toolbar4 = findViewById(R.id.toolbar4);
        ImageButton imageButtonBack4 = findViewById(R.id.imageButtonBack4);
        TextView textViewStatsSource4 = findViewById(R.id.textViewStatsSource4);
        TextView textViewScreen4 = findViewById(R.id.textViewScreen4);
        lineChart = findViewById(R.id.lineChart);

        // background colour
        lineChart.setBackgroundColor(Color.WHITE);

        // Disable description text
        lineChart.getDescription().setEnabled(true);

        // Enabling Touch Gestures
        lineChart.setTouchEnabled(true);

        lineChart.setDrawGridBackground(false);

        // Enable Drag
        lineChart.setDragEnabled(true);
        // Enable Zoom
        lineChart.setScaleEnabled(true);
        // lineChart.setScaleXEnabled(true);
        // lineChart.setScaleYEnabled(true);

        // Scaling along both axes
        lineChart.setPinchZoom(true);
        //  animate
        lineChart.animateXY(2000,2000);
        // Setting up a listener
//        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });

        imageButtonBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setData();
//        setMarker();
    }

    //Load data
    public void setData(){
        ArrayList<PopulationData> populationDataArrayList = DataManager.getInstance().getPopulationDataArrayList();

        if (populationDataArrayList == null || populationDataArrayList.isEmpty())
            return;

        int[] populationArray = new int[populationDataArrayList.size()];
        for (int i = 0; i < populationDataArrayList.size(); i++) {
            PopulationData data = populationDataArrayList.get(i);
            populationArray[i] = data.getPopulation();
        }

        //In MPAndroidChart is generally loaded with data through List<Entry> objects
        List<Entry> entries = new ArrayList<Entry>();
        // loop to retrieve data
        for(int i = 0; i < populationArray.length; i++){
            entries.add(new Entry(i,populationArray[i]));
        }
        //A LineDataSet object is a curve.
        LineDataSet lineDataSet = new LineDataSet(entries," The first data");
        //LineData is the real data for LineChart.
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    //Click on the indicator to show more details
//    public void setMarker(){
//        // create marker to display box when values are selected
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        // Set the marker to the chart
//        mv.setChartView(chart);
//        lineChart.setMarker(mv);
//    }
}
