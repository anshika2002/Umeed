package com.example.test.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FullPost extends AppCompatActivity {

    ImageView img1;
    TextView tvv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);

        img1 = findViewById(R.id.full);
        tvv1 = findViewById(R.id.full_caption);

        Glide.with(this).load(getIntent().getStringExtra("link")).into(img1);
        tvv1.setText(getIntent().getStringExtra("caption"));


    }
}