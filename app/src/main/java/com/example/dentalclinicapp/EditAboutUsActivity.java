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
import com.example.dentalclinicapp.Utility.AboutUsData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class EditAboutUsActivity extends AppCompatActivity {
    Button update_contact_us;
    EditText edt_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_about_us);
        update_contact_us = findViewById(R.id.update_contact_us);
        edt_about = findViewById(R.id.edt_about);
        edt_about.setText(AboutUsData.collection.get(0).getDetails());
        update_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_about.isEnabled()){
                    if (edt_about.getText().toString().isEmpty()){
                        Toast.makeText(EditAboutUsActivity.this, "Please enter about us details", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        edit_about();
                    }
                }
                else{
                    edt_about.setEnabled(true);
                    Toast.makeText(EditAboutUsActivity.this, "Edit the about us details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void edit_about() {
        RequestQueue requestQueue = Volley.newRequestQueue(EditAboutUsActivity.this);
        String url = ProjectAPI.EDIT_ABOUT_US;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response.equalsIgnoreCase("success")){
                        Toast.makeText(EditAboutUsActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        edt_about.setEnabled(false);
                    }
                    else {
                        Toast.makeText(EditAboutUsActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(EditAboutUsActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditAboutUsActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("details",edt_about.getText().toString());
                return params;
            }
        };
        requestQueue.add(request);
    }
}