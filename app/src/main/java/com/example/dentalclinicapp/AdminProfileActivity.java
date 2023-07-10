package com.example.dentalclinicapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentalclinicapp.Utility.AdminData;
import com.example.dentalclinicapp.Utility.UserData;

public class AdminProfileActivity extends AppCompatActivity {
    TextView txt_username,txt_name,txt_email,txt_mobile,txt_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        txt_address = findViewById(R.id.txt_address);
        txt_email = findViewById(R.id.txt_email);
        txt_mobile = findViewById(R.id.txt_mobile);
        txt_name = findViewById(R.id.txt_name);
        txt_username = findViewById(R.id.txt_username);
        txt_username.setText("Username: " + AdminData.collection.get(0).getUsername());
        txt_name.setText("Name: " + AdminData.collection.get(0).getName());
        txt_email.setText("Email: " + AdminData.collection.get(0).getEmail());
        txt_mobile.setText("Mobile: " + AdminData.collection.get(0).getMobile());
        txt_address.setText("Address:\n" + AdminData.collection.get(0).getAddress());
    }
}