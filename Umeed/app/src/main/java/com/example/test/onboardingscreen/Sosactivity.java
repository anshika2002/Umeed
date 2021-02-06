package com.example.test.onboardingscreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class Sosactivity extends AppCompatActivity {

    public static TextView counter;
    Button start,stop;
    EditText phone;
    private static final String PREFS_NAME = "Phone_pref";
    Button save;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosactivity);

        counter = (TextView) findViewById(R.id.counter);
        start = findViewById(R.id.start_service);
        stop = findViewById(R.id.stop_service);
        phone = findViewById(R.id.phone_num);
        save = findViewById(R.id.save_phone);

        phone.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

        if (getPreference(getApplicationContext(),"Pref_key") == null){
            phone.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (!((checkSelfPermission(Manifest.permission.SEND_SMS ) == PackageManager.PERMISSION_GRANTED)
                    && (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
                    && (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED))){
                requestPermissions(new String[] {Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString() == null){
                    phone.setError("Please ");
                }else {
                    setPreference(getApplicationContext(),"Pref_key",phone.getText().toString());
                    Toast.makeText(Sosactivity.this, "Number Saved", Toast.LENGTH_SHORT).show();
                    phone.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.INVISIBLE);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sosactivity.this, shake.class);
                //Start Service
                //startService(intent);
                intent.setAction("START_SERVICE");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getApplicationContext().startForegroundService(intent);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sosactivity.this, shake.class);
                //Start Service
                //startService(intent);
                intent.setAction("STOP_SERVICE");
                startService(intent);
            }
        });
    }

    public static void setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }
}