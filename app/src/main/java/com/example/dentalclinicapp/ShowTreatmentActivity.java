package com.example.dentalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dentalclinicapp.Adapter.TreatmentDetailsAdapter;
import com.example.dentalclinicapp.Utility.ProjectAPI;

public class ShowTreatmentActivity extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_treatment);
        list = findViewById(R.id.list);
        TreatmentDetailsAdapter adapter = new TreatmentDetailsAdapter(ShowTreatmentActivity.this);
        list.setAdapter(adapter);
    }
}