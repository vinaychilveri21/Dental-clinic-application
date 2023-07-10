package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentalclinicapp.Utility.UserData;

public class UserProfileActivity extends AppCompatActivity {
    TextView txt_username,txt_name,txt_email,txt_mobile,txt_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        txt_username = findViewById(R.id.txt_username);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_mobile = findViewById(R.id.txt_Mobile);
        txt_address = findViewById(R.id.txt_address);

        txt_username.setText("Username: "+ UserData.collection.get(0).getUsername());
        txt_name.setText("Name: "+UserData.collection.get(0).getName());
        txt_email.setText("Email: "+UserData.collection.get(0).getEmail());
        txt_mobile.setText("Mobile: "+UserData.collection.get(0).getMobile());
        txt_address.setText("Address:\n"+UserData.collection.get(0).getAddress());
    }

    public void user_update(View view) {
        Intent intent=new Intent(getApplicationContext(),UserUpdateActivity.class);
        startActivity(intent);

    }
}