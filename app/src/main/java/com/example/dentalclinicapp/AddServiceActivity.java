package com.example.dentalclinicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class AddServiceActivity extends AppCompatActivity {
    EditText edt_service_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        edt_service_name = findViewById(R.id.edt_service_name);
    }

    public void insert(View view) {
        if (edt_service_name.getText().toString().isEmpty())
            Toast.makeText(AddServiceActivity.this, "Please enter service name", Toast.LENGTH_SHORT).show();
        else{
            if (edt_service_name.getText().toString().matches("[a-zA-Z /s]+")){
                RequestQueue requestQueue = Volley.newRequestQueue(AddServiceActivity.this);
                String url = ProjectAPI.ADD_SERVICE;
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.equalsIgnoreCase("success")){
                                Toast.makeText(AddServiceActivity.this, "Service Add successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(AddServiceActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            Toast.makeText(AddServiceActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddServiceActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("service_name",edt_service_name.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(request);
            }
            else {
                Toast.makeText(AddServiceActivity.this, "Please enter valid service name", Toast.LENGTH_SHORT).show();
            }
        }
    }
}