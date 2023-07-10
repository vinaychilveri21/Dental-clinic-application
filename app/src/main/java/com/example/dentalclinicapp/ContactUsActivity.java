package com.example.dentalclinicapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Utility.ContactUsData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import org.json.JSONArray;

public class ContactUsActivity extends AppCompatActivity {
    TextView txt_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        txt_contact = findViewById(R.id.txt_contact);
        txt_contact.setText(ContactUsData.collection.get(0).getDetails());
    }
}