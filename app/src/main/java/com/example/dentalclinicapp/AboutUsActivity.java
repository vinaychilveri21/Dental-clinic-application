package com.example.dentalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dentalclinicapp.Utility.AboutUsData;

public class AboutUsActivity extends AppCompatActivity {
    TextView txt_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        txt_about = findViewById(R.id.txt_about);
        txt_about.setText(AboutUsData.collection.get(0).getDetails());
    }
}