package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Utility.AboutUsData;
import com.example.dentalclinicapp.Utility.AppointmentData;
import com.example.dentalclinicapp.Utility.ContactUsData;
import com.example.dentalclinicapp.Utility.OpdData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.ServiceData;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

public class UserHomeActivity extends AppCompatActivity {
    int user_id = UserData.collection.get(0).getId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        get_service();
        get_opd();
        get_appointment();
        get_contact_us();
        get_about_us();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        get_service();
        get_opd();
        get_appointment();
        get_contact_us();
        get_about_us();
    }

    private void get_about_us() {
        String url = ProjectAPI.GET_ABOUT_US;
        RequestQueue requestQueue = Volley.newRequestQueue(UserHomeActivity.this);
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
                        //Toast.makeText(UserHomeActivity.this, "Contact details fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                      //  Toast.makeText(UserHomeActivity.this, "Server message: "+response.getJSONObject(0).getString("status"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    //Toast.makeText(UserHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_contact_us() {
        String url = ProjectAPI.GET_CONTACT_US;
        RequestQueue requestQueue = Volley.newRequestQueue(UserHomeActivity.this);
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
                        //Toast.makeText(UserHomeActivity.this, "Contact details fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                       // Toast.makeText(UserHomeActivity.this, "Server message: "+response.getJSONObject(0).getString("status"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                  //  Toast.makeText(UserHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void get_appointment() {

        RequestQueue requestQueue = Volley.newRequestQueue(UserHomeActivity.this);
        String url = ProjectAPI.GET_USER_APPOINTMENTS+"?user_id="+ UserData.collection.get(0).getId();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if(response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        AppointmentData.collection.clear();
                        for (int i=1; i<response.length()-1; i++){
                            AppointmentData appointmentData = new AppointmentData();
                            appointmentData.setId(response.getJSONObject(i).getInt("id"));
                            appointmentData.setUser_id(response.getJSONObject(i).getInt("user_id"));
                            appointmentData.setTreatment(response.getJSONObject(i).getString("treatment"));
                            appointmentData.setMedicines(response.getJSONObject(i).getString("medicines"));
                            appointmentData.setDate(response.getJSONObject(i).getString("date"));
                            appointmentData.setFees(response.getJSONObject(i).getInt("fees"));
                            appointmentData.setStatus(response.getJSONObject(i).getString("status"));
                            appointmentData.setOpd_id(response.getJSONObject(i).getInt("opd_id"));
                            AppointmentData.collection.add(appointmentData);
                        }
                        //Toast.makeText(UserHomeActivity.this, "Appointment added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                      //  Toast.makeText(UserHomeActivity.this, response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                  //  Toast.makeText(UserHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);

    }

    private void get_opd() {
        String url = ProjectAPI.GET_OPD+"?user_id="+user_id;
        RequestQueue requestQueue = Volley.newRequestQueue(UserHomeActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        OpdData.collection.clear();
                        for (int i=1; i<response.length()-1;i++){
                            OpdData opdData = new OpdData();
                            opdData.setId(response.getJSONObject(i).getInt("id"));
                            opdData.setUser_id(response.getJSONObject(i).getInt("user_id"));
                            opdData.setDatetime(response.getJSONObject(i).getString("datetime"));
                            opdData.setService_id(response.getJSONObject(i).getInt("service_id"));
                            OpdData.collection.add(opdData);
                        }
                        //Toast.makeText(UserHomeActivity.this, "OPD added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Toast.makeText(UserHomeActivity.this, response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                  //  Toast.makeText(UserHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);

    }

    private void get_service() {

        RequestQueue requestQueue = Volley.newRequestQueue(UserHomeActivity.this);
        String url = ProjectAPI.GET_SERVICE;
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        ServiceData.collection.clear();
                        for (int i = 1; i < response.length()-1; i++){
                            int ser_id = Integer.parseInt(response.getJSONObject(i).getString("service_id"));
                            String ser_name = response.getJSONObject(i).getString("service_name");
                            String ser_status = response.getJSONObject(i).getString("service_status");
                            ServiceData serviceData = new ServiceData();
                            serviceData.setId(ser_id);
                            serviceData.setService_name(ser_name);
                            serviceData.setService_status(ser_status);
                            ServiceData.collection.add(serviceData);

                        }
                        //Toast.makeText(UserHomeActivity.this, "Service added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    //    Toast.makeText(UserHomeActivity.this, response.getJSONObject(0).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                   // Toast.makeText(UserHomeActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserHomeActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);

    }

    public void service(View view) {
        Intent intent = new Intent(UserHomeActivity.this,ServiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void appointment(View view) {
        Intent intent = new Intent(UserHomeActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void contact_us(View view) {
        Intent intent = new Intent(UserHomeActivity.this,ContactUsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void about_us(View view) {
        Intent intent = new Intent(UserHomeActivity.this,AboutUsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(UserHomeActivity.this,UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        UserData.collection.clear();
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}