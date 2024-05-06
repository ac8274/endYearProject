package com.example.endyearproject;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    boolean isFirstTime = false;
    boolean adding;
    @Override
    protected void onStart() {
        super.onStart();
        isFirstTime = true;
        adding = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isFirstTime)
        {
            Intent gi = new Intent();
            Intent ig = getIntent();
        }
    }
}