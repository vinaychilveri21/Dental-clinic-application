package com.example.dentalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dentalclinicapp.Utility.UserData;

public class UserUpdateActivity extends AppCompatActivity {
    EditText edt_name,edt_email,edt_mobile,edt_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_address=findViewById(R.id.edt_address);
        edt_name.setText(UserData.collection.get(0).getName());
        edt_email.setText(UserData.collection.get(0).getEmail());
        edt_mobile.setText(UserData.collection.get(0).getMobile());
        edt_address.setText(UserData.collection.get(0).getAddress());


    }

    public void userdata_update(View view) {
        //edt_name.setText();
    }
}