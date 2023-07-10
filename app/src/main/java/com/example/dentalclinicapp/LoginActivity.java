package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Utility.AdminData;
import com.example.dentalclinicapp.Utility.DoctorData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

public class LoginActivity extends AppCompatActivity {
    EditText euser,epass;
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        euser=findViewById(R.id.edt_user);
        epass=findViewById(R.id.edt_pass);
        p = findViewById(R.id.progress);

    }

    public void login(View view) {
        p.setVisibility(View.VISIBLE);
        boolean log ;
        if(euser.getText().toString().isEmpty() )
        {
            euser.setError("please enter username");
            log = false;
        }
        else {
            log=true;
        }
        if(epass.getText().toString().isEmpty() )
        {
            epass.setError("please enter password");
            log = log & false;
        }
        else {
            log = log & true;
        }
        if(log == true){
            String url = ProjectAPI.LOGIN_USER+"?username="+euser.getText().toString();
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                            if(epass.getText().toString().equalsIgnoreCase(response.getJSONObject(1).getString("password"))){
                                UserData.collection.clear();
                                UserData userData = new UserData();
                                userData.setId(response.getJSONObject(1).getInt("user_id"));
                                userData.setName(response.getJSONObject(1).getString("name"));
                                userData.setMobile(response.getJSONObject(1).getString("mobile"));
                                userData.setEmail(response.getJSONObject(1).getString("email"));
                                userData.setAddress(response.getJSONObject(1).getString("address"));
                                userData.setStatus(response.getJSONObject(1).getString("status"));
                                userData.setUsername(response.getJSONObject(1).getString("username"));
                                userData.setPassword(response.getJSONObject(1).getString("password"));
                                UserData.collection.add(userData);
                                p.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,UserHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {

                                p.setVisibility(View.GONE);
                                epass.setError("Password does not match");
                            }
                        }
                        else if(response.getJSONObject(0).getString("status").equalsIgnoreCase("block")){
                            p.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            admin_login();
                        }
                    }catch (Exception e){
                        p.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    p.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }
        else {
            p.setVisibility(View.GONE);
        }
        







    }

    private void admin_login() {
        String url = ProjectAPI.LOGIN_ADMIN+"?username="+euser.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        if(epass.getText().toString().equalsIgnoreCase(response.getJSONObject(1).getString("password"))){
                            AdminData.collection.clear();
                            AdminData adminData = new AdminData();
                            adminData.setId(response.getJSONObject(1).getInt("id"));
                            adminData.setName(response.getJSONObject(1).getString("name"));
                            adminData.setMobile(response.getJSONObject(1).getString("mobile"));
                            adminData.setEmail(response.getJSONObject(1).getString("email"));
                            adminData.setAddress(response.getJSONObject(1).getString("address"));
                            adminData.setStatus(response.getJSONObject(1).getString("status"));
                            adminData.setUsername(response.getJSONObject(1).getString("username"));
                            adminData.setPassword(response.getJSONObject(1).getString("password"));
                            AdminData.collection.add(adminData);
                            p.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,AdminHomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            p.setVisibility(View.GONE);
                            epass.setError("Admin Password does not match");
                        }
                    }
                    else if(response.getJSONObject(0).getString("status").equalsIgnoreCase("block")){
                        p.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        doctor_login();
                    }
                }catch (Exception e){
                    p.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Admin Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                p.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Admin Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void doctor_login() {
        String url = ProjectAPI.LOGIN_DOCTOR+"?username="+euser.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        if(epass.getText().toString().equalsIgnoreCase(response.getJSONObject(1).getString("password"))){
                            DoctorData.collection.clear();
                            DoctorData doctorData = new DoctorData();
                            doctorData.setId(response.getJSONObject(1).getInt("id"));
                            doctorData.setName(response.getJSONObject(1).getString("name"));
                            doctorData.setUsername(response.getJSONObject(1).getString("username"));
                            doctorData.setPassword(response.getJSONObject(1).getString("password"));
                            DoctorData.collection.add(doctorData);
                            p.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,DoctorHomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            p.setVisibility(View.GONE);
                            epass.setError("Doctor Password does not match");
                        }
                    }
                    else {
                        p.setVisibility(View.GONE);
                        euser.setError("username not found please Register");
                    }
                }catch (Exception e){
                    p.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Doctor Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                p.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Doctor Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public void createReg(View view) {

        Intent reg=new Intent(getApplicationContext(),RegistrationActivity.class);
        reg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(reg);
        finish();
    }
}