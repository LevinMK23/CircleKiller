package com.samsung.circlekiller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BackGroundImageView(this));
    }
}