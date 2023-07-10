package com.example.dentalclinicapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.DoctorAppointmentAdapter;
import com.example.dentalclinicapp.Utility.DoctorAppointmentData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import org.json.JSONArray;

import java.util.Calendar;

public class DoctorAppointmentListActivity extends AppCompatActivity {
    ImageView select_date;
    ListView list_appointment;

    Calendar calendar;
    int y,m,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appintment_list);
        list_appointment=findViewById(R.id.list_appointment);
        select_date = findViewById(R.id.select_date);
        DoctorAppointmentAdapter adapter=new DoctorAppointmentAdapter(DoctorAppointmentListActivity.this);
        list_appointment.setAdapter(adapter);
        calendar= Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);
        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DoctorAppointmentListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String url = ProjectAPI.GET_APPOINTMENT_LIST;
                        RequestQueue requestQueue = Volley.newRequestQueue(DoctorAppointmentListActivity.this);
                        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                                        DoctorAppointmentData.collection.clear();
                                        boolean count=false;
                                        String date = dayOfMonth+"/"+(month+1)+"/"+year;
                                        for (int i=1; i< response.length()-1; i++){

                                            if (response.getJSONObject(i).getString("date").equalsIgnoreCase(date)){
                                                DoctorAppointmentData doctorAppointmentData = new DoctorAppointmentData();
                                                doctorAppointmentData.setId(response.getJSONObject(i).getInt("id"));
                                                doctorAppointmentData.setUser_id(response.getJSONObject(i).getInt("user_id"));
                                                doctorAppointmentData.setTreatment(response.getJSONObject(i).getString("treatment"));
                                                doctorAppointmentData.setDate(response.getJSONObject(i).getString("date"));
                                                doctorAppointmentData.setFees(response.getJSONObject(i).getInt("fees"));
                                                doctorAppointmentData.setOpd_id(response.getJSONObject(i).getInt("opd_id"));
                                                doctorAppointmentData.setService_name(response.getJSONObject(i).getString("service_name"));
                                                doctorAppointmentData.setUsername(response.getJSONObject(i).getString("user_name"));
                                                DoctorAppointmentData.collection.add(doctorAppointmentData);
                                                count=true;
                                            }
                                        }
                                        if (count==true) {
                                            DoctorAppointmentAdapter adapter = new DoctorAppointmentAdapter(DoctorAppointmentListActivity.this);
                                            list_appointment.setAdapter(adapter);
                                        }
                                        else {
                                            Toast.makeText(DoctorAppointmentListActivity.this, "No Appointments found", Toast.LENGTH_SHORT).show();
                                            DoctorAppointmentData.collection.clear();
                                            DoctorAppointmentAdapter adapter = new DoctorAppointmentAdapter(DoctorAppointmentListActivity.this);
                                            list_appointment.setAdapter(adapter);
                                        }
                                    }
                                    else {
                                        Toast.makeText(DoctorAppointmentListActivity.this, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    Toast.makeText(DoctorAppointmentListActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DoctorAppointmentListActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(request);
                        DoctorAppointmentAdapter adapter=new DoctorAppointmentAdapter(DoctorAppointmentListActivity.this);
                        list_appointment.setAdapter(adapter);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        String url = ProjectAPI.GET_APPOINTMENT_LIST;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorAppointmentListActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        DoctorAppointmentData.collection.clear();
                        for (int i=1; i< response.length()-1; i++){
                            DoctorAppointmentData doctorAppointmentData = new DoctorAppointmentData();
                            doctorAppointmentData.setId(response.getJSONObject(i).getInt("id"));
                            doctorAppointmentData.setUser_id(response.getJSONObject(i).getInt("user_id"));
                            doctorAppointmentData.setTreatment(response.getJSONObject(i).getString("treatment"));
                            doctorAppointmentData.setDate(response.getJSONObject(i).getString("date"));
                            doctorAppointmentData.setFees(response.getJSONObject(i).getInt("fees"));
                            doctorAppointmentData.setOpd_id(response.getJSONObject(i).getInt("opd_id"));
                            doctorAppointmentData.setService_name(response.getJSONObject(i).getString("service_name"));
                            doctorAppointmentData.setUsername(response.getJSONObject(i).getString("user_name"));
                            DoctorAppointmentData.collection.add(doctorAppointmentData);
                        }
                        //Toast.makeText(DoctorAppointmentListActivity.this, "Appointment Data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorAppointmentListActivity.this, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorAppointmentListActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorAppointmentListActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        DoctorAppointmentAdapter adapter=new DoctorAppointmentAdapter(DoctorAppointmentListActivity.this);
        list_appointment.setAdapter(adapter);
    }
}