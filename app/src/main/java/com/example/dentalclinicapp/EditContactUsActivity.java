package com.example.dentalclinicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Utility.ContactUsData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class EditContactUsActivity extends AppCompatActivity {
    Button update_contact_us;
    EditText edt_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_us);
        update_contact_us = findViewById(R.id.update_contact_us);
        edt_contact = findViewById(R.id.edt_contact);
        edt_contact.setText(ContactUsData.collection.get(0).getDetails());
        update_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_contact.isEnabled()){
                    if (edt_contact.getText().toString().isEmpty()){
                        Toast.makeText(EditContactUsActivity.this, "Please enter contact details", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        edit_contact();
                    }
                }
                else{
                    edt_contact.setEnabled(true);
                    Toast.makeText(EditContactUsActivity.this, "Edit the contact us details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void edit_contact() {
        RequestQueue requestQueue = Volley.newRequestQueue(EditContactUsActivity.this);
        String url = ProjectAPI.EDIT_CONTACT_US;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response.equalsIgnoreCase("success")){
                        Toast.makeText(EditContactUsActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        edt_contact.setEnabled(false);
                    }
                    else {
                        Toast.makeText(EditContactUsActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(EditContactUsActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditContactUsActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("details",edt_contact.getText().toString());
                return params;
            }
        };
        requestQueue.add(request);
    }
}