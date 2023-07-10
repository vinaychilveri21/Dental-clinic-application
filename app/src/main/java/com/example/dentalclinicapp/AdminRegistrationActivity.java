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

public class AdminRegistrationActivity extends AppCompatActivity {

    EditText ename,emob,email,eadrs,euser,epass,cpass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);
        ename=findViewById(R.id.edt_name);
        emob=findViewById(R.id.edt_mob);
        email=findViewById(R.id.edt_mail);
        eadrs=findViewById(R.id.edt_adrs);
        euser=findViewById(R.id.edt_user);
        epass=findViewById(R.id.edt_pass);
        cpass=findViewById(R.id.edt_cpass);
    }

    public void register(View view) {
        int flag = 0;


        if(ename.getText().toString().isEmpty())
        {
            ename.setError("Please enter name");

        }


        if(emob.getText().toString().isEmpty())
        {
            emob.setError("Please enter mobile no");

        }
        if(email.getText().toString().isEmpty())
        {
            email.setError("Please enter email");
        }
        if(eadrs.getText().toString().isEmpty())
        {
            eadrs.setError("Please enter address");
        }
        if(euser.getText().toString().isEmpty())
        {
            euser.setError("Please enter username");
        }
        if(epass.getText().toString().isEmpty())
        {
            epass.setError("Please enter password");
        }
        if(cpass.getText().toString().isEmpty())
        {
            cpass.setError("Please enter confirm password");
        }

        if(!ename.getText().toString().matches("[a-zA-Z ]+"))
        {
            flag++;
            ename.setError("Name should contain alphabets only ");
        }
        if(!emob.getText().toString().matches("[0-9]+"))
        {
            flag++;
            emob.setError("enter valid mobile no");
        }

        if(emob.getText().toString().length()!=10)
        {
            flag++;
            emob.setError("mobile no must be 10 digits");
        }
        if(email.getText().toString().matches("[a-z][a-zA-Z0-9.#$!^&*()_<>;:']+@[a-z]+.[a-z]+")==false)
        {
            flag++;
            email.setError("Please enter valid email");
        }
        if(!eadrs.getText().toString().matches("[a-zA-Z/.0-9, -/s]+"))
        {
            flag++;
            eadrs.setError("Please enter valid address");
        }
        if(!euser.getText().toString().matches("[.][a-zA-Z0-9@.!#$%^&*()_+/,:;]+"))
        {
            flag++;
            euser.setError("Please start admin username with .(dot)");
        }
        if(!epass.getText().toString().matches("[a-zA-Z0-9@.!#$%^&*()_+/,:;]+"))
        {
            flag++;
            epass.setError("Please enter valid password");
        }

        if(!(ename.getText().toString().isEmpty() && emob.getText().toString().isEmpty() && email.getText().toString().isEmpty() && eadrs.getText().toString().isEmpty() && euser.getText().toString().isEmpty() && epass.getText().toString().isEmpty() && cpass.getText().toString().isEmpty()))
        {
            if (!(epass.getText().toString().equals(cpass.getText().toString()))) {
                cpass.setError("Confirm password does not match with password");
            }
            if(flag==0) {
                if (epass.getText().toString().equals(cpass.getText().toString())) {
                    String name,mobile,mail,address,username,password;
                    name = ename.getText().toString();
                    mobile = emob.getText().toString();
                    mail = email.getText().toString();
                    address = eadrs.getText().toString();
                    username = euser.getText().toString();
                    password = epass.getText().toString();
                    String url = ProjectAPI.ADD_ADMIN;
                    RequestQueue requestQueue = Volley.newRequestQueue(AdminRegistrationActivity.this);
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("success")){
                                Toast.makeText(AdminRegistrationActivity.this, "Admin added successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(AdminRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminRegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @org.jetbrains.annotations.Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> param = new HashMap<String, String>();
                            param.put("name",name);
                            param.put("mobile",mobile);
                            param.put("email",mail);
                            param.put("address",address);
                            param.put("username",username);
                            param.put("password",password);
                            return param;
                        }
                    };
                    requestQueue.add(request);
                }
            }
            else{
                Toast.makeText(this, "Please enter valid input", Toast.LENGTH_SHORT).show();
            }

        }
        else {

            Toast.makeText(getApplicationContext(), "some data are not filled", Toast.LENGTH_LONG).show();
        }



    }
}

