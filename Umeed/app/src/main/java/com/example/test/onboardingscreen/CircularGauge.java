package com.example.test.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class CircularGauge extends AppCompatActivity {

    private PieChart pchart;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_gauge);

        button = findViewById(R.id.neextt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircularGauge.this,Form.class);
                startActivity(intent);
            }
        });

        pchart=(PieChart)findViewById(R.id.chart);
        pchart.setBackgroundColor(Color.WHITE);

        moveOffScreen();

        pchart.setUsePercentValues(true);
        pchart.getDescription().setEnabled(false);
        pchart.setDrawHoleEnabled(true);

        pchart.setMaxAngle(180);
        pchart.setRotationAngle(180);
        pchart.setCenterTextOffset(0,-20);
        setData(4,100);

        pchart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        Legend l=pchart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(50f);

        pchart.setEntryLabelColor(Color.WHITE);
        pchart.setEntryLabelTextSize(12f);

    }

    String[] measuring=new String[]{"Low","Normal","Medium","High"};
    private void setData(int count,int range){
        ArrayList<PieEntry>values=new ArrayList<>();

        for(int i=0;i<count;i++){
            float val=(float)((Math.random()+range)+range/5);
            values.add(new PieEntry(val,measuring[i]));
        }

        PieDataSet dataSet=new PieDataSet(values,"Partner");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(3f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data=new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        pchart.setData(data);
        pchart.invalidate();

    }

    private void moveOffScreen(){
        Display display=getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height=metrics.heightPixels;
        int offset= (int) (height*0.1);

        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)pchart.getLayoutParams();
        params.setMargins(0,0,0,-offset);
        pchart.setLayoutParams(params);
    }
}