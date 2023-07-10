package com.example.dentalclinicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentalclinicapp.Adapter.ServiceAdapter;

public class ServiceActivity extends AppCompatActivity {
    ListView list_service;
    public static ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        list_service=findViewById(R.id.list_service);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        ServiceAdapter adapter = new ServiceAdapter(ServiceActivity.this);
        list_service.setAdapter(adapter);
        pb.setVisibility(View.GONE);
    }

}