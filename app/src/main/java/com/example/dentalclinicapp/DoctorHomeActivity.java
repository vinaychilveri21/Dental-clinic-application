package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.PatientListAdapter;
import com.example.dentalclinicapp.Utility.AboutUsData;
import com.example.dentalclinicapp.Utility.AdminListData;
import com.example.dentalclinicapp.Utility.ContactUsData;
import com.example.dentalclinicapp.Utility.DoctorAppointmentData;
import com.example.dentalclinicapp.Utility.DoctorData;
import com.example.dentalclinicapp.Utility.PatientListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.ServiceData;

import org.json.JSONArray;

public class DoctorHomeActivity extends AppCompatActivity {
    Button btn_service,btn_appointment,btn_pnt_lst,btn_admin,btn_cnt_us,btn_abt_us,btn_app_hist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        btn_service = findViewById(R.id.btn_service);
        btn_pnt_lst = findViewById(R.id.btn_pnt_lst);
        btn_admin = findViewById(R.id.btn_admin);
        btn_cnt_us = findViewById(R.id.btn_cnt_us);
        btn_abt_us = findViewById(R.id.btn_abt_us);
        btn_app_hist = findViewById(R.id.btn_app_hist);
        get_appointments();
        get_services();
        get_patient_list();
        get_admins();
        get_contact_us();
        get_about_us();
        btn_appointment = findViewById(R.id.btn_appointment);
        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,DoctorServiceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,DoctorAppointmentListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_pnt_lst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,PatientListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,AddAdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_cnt_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,EditContactUsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_abt_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,EditAboutUsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btn_app_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorHomeActivity.this, "Appointment Historyc ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        get_appointments();
        get_services();
        get_patient_list();
        get_admins();
        get_contact_us();
        get_about_us();
    }

    private void get_about_us() {
        String url = ProjectAPI.GET_ABOUT_US;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        AboutUsData.collection.clear();
                        AboutUsData aboutUsData = new AboutUsData();
                        aboutUsData.setId(response.getJSONObject(1).getInt("id"));
                        aboutUsData.setDetails(response.getJSONObject(1).getString("details"));
                        AboutUsData.collection.add(aboutUsData);
                        //Toast.makeText(DoctorHomeActivity.this, "Contact details fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorHomeActivity.this, "Server message: "+response.getJSONObject(0).getString("status"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_contact_us() {
        String url = ProjectAPI.GET_CONTACT_US;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        ContactUsData.collection.clear();
                        ContactUsData contactUsData = new ContactUsData();
                        contactUsData.setId(response.getJSONObject(1).getInt("id"));
                        contactUsData.setDetails(response.getJSONObject(1).getString("details"));
                        ContactUsData.collection.add(contactUsData);
                        //Toast.makeText(DoctorHomeActivity.this, "Contact details fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorHomeActivity.this, "Server message: "+response.getJSONObject(0).getString("status"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_admins() {
        String url = ProjectAPI.GET_ADMINS;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        AdminListData.collection.clear();
                        for (int i=1; i<response.length()-1;i++){
                            AdminListData adminListData = new AdminListData();
                            adminListData.setId(response.getJSONObject(i).getInt("id"));
                            adminListData.setName(response.getJSONObject(i).getString("name"));
                            adminListData.setMobile(response.getJSONObject(i).getString("mobile"));
                            adminListData.setEmail(response.getJSONObject(i).getString("email"));
                            adminListData.setAddress(response.getJSONObject(i).getString("address"));
                            adminListData.setStatus(response.getJSONObject(i).getString("status"));
                            adminListData.setUsername(response.getJSONObject(i).getString("username"));
                            AdminListData.collection.add(adminListData);
                        }
                        //Toast.makeText(DoctorHomeActivity.this, "All Users data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_patient_list() {
        String url = ProjectAPI.GET_USERS;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        PatientListData.collection.clear();
                        for (int i=1; i<response.length()-1;i++){
                            PatientListData patientListData = new PatientListData();
                            patientListData.setId(response.getJSONObject(i).getInt("id"));
                            patientListData.setName(response.getJSONObject(i).getString("name"));
                            patientListData.setMobile(response.getJSONObject(i).getString("mobile"));
                            patientListData.setEmail(response.getJSONObject(i).getString("email"));
                            patientListData.setAddress(response.getJSONObject(i).getString("address"));
                            patientListData.setStatus(response.getJSONObject(i).getString("status"));
                            patientListData.setUsername(response.getJSONObject(i).getString("username"));
                            PatientListData.collection.add(patientListData);
                        }
                        //Toast.makeText(DoctorHomeActivity.this, "Patient data fetched successfully", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(DoctorHomeActivity.this, "All Users data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_appointments() {
        String url = ProjectAPI.GET_APPOINTMENT_LIST;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
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
                        //Toast.makeText(DoctorHomeActivity.this, "Appointment Data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorHomeActivity.this, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_services() {
        String url = ProjectAPI.GET_ALL_SERVICE;
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorHomeActivity.this);
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
                        //Toast.makeText(DoctorHomeActivity.this, "Service Data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DoctorHomeActivity.this, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(DoctorHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public void doc_logout(View view) {
        DoctorData.collection.clear();
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}