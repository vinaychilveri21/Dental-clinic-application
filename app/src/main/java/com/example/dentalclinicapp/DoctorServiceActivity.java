package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.DoctorServiceAdapter;
import com.example.dentalclinicapp.Adapter.ServiceAdapter;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.ServiceData;

import org.json.JSONArray;


public class DoctorServiceActivity extends AppCompatActivity {

    ListView list_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_service);
        list_service=findViewById(R.id.list_service);
        DoctorServiceAdapter adapter =new DoctorServiceAdapter(DoctorServiceActivity.this);
        list_service.setAdapter(adapter);
    }

    public void insert_data(View view) {
        Intent intent = new Intent(DoctorServiceActivity.this,AddServiceActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = ProjectAPI.GET_ALL_SERVICE;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorServiceActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        ServiceData.collection.clear();
                        for (int i=1; i< response.length()-1; i++){
                            ServiceData serviceData = new ServiceData();
                            serviceData.setId(response.getJSONObject(i).getInt("service_id"));
                            serviceData.setService_name(response.getJSONObject(i).getString("service_name"));
                            serviceData.setService_status(response.getJSONObject(i).getString("service_status"));
                            ServiceData.collection.add(serviceData);
                        }
                        //Toast.makeText(DoctorServiceActivity.this, "Service Data fetched successfully", Toast.LENGTH_SHORT).show();
                        DoctorServiceAdapter adapter =new DoctorServiceAdapter(DoctorServiceActivity.this);
                        list_service.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(DoctorServiceActivity.this, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorServiceActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorServiceActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}