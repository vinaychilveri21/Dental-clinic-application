package com.example.dentalclinicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.MyAppointmentAdapter;
import com.example.dentalclinicapp.Utility.OpdServiceData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    ProgressBar p;

    ListView myAppointmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAppointmentList=findViewById(R.id.myAppointmentList);
        p = findViewById(R.id.progress);
        get_service_name();
    }

    private void get_service_name() {
        p.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        int user_id = UserData.collection.get(0).getId();
        String url = ProjectAPI.GET_SERVICE_NAME+"?user_id="+user_id;
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        OpdServiceData.collection.clear();
                        for (int i=1; i<response.length()-1;i++) {
                            OpdServiceData opdServiceData = new OpdServiceData();
                            opdServiceData.setOpd_id(response.getJSONObject(i).getInt("opd_id"));
                            opdServiceData.setService_name(response.getJSONObject(i).getString("service_name"));
                            opdServiceData.setDatetime(response.getJSONObject(i).getString("datetime"));
                            OpdServiceData.collection.add(opdServiceData);
                        }
                        //Toast.makeText(MainActivity.this, "Appointment service added successfully", Toast.LENGTH_SHORT).show();
                        MyAppointmentAdapter adapter = new MyAppointmentAdapter(MainActivity.this);
                        myAppointmentList.setAdapter(adapter);
                        p.setVisibility(View.GONE);
                    }
                    else {
                        p.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "No Appointment Found", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    p.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                p.setVisibility(View.GONE);
            }
        });
        requestQueue.add(request);
    }
}