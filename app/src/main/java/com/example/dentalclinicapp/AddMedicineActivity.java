package com.example.dentalclinicapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMedicineActivity extends AppCompatActivity {
    EditText username;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.edt_medicine);
        add=findViewById(R.id.btn_add);
    }
}