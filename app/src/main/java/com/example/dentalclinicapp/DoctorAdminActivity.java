package com.example.dentalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorAdminActivity extends AppCompatActivity {

    EditText ename,email,emob,euser,epass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_admin);
        ename=findViewById(R.id.edt_name);
        email=findViewById(R.id.edt_mail);
        emob=findViewById(R.id.edt_mob);
        euser=findViewById(R.id.edt_uname);
        epass=findViewById(R.id.edt_paswrd);
    }

    public void save(View view) {

        int cnt=0;

        if(ename.getText().toString().isEmpty())
        {
            ename.setError("please enter name");
        }
        if(email.getText().toString().isEmpty())
        {
            email.setError("please enter email");
        }
        if(emob.getText().toString().isEmpty())
        {
            emob.setError("please enter mobileno");
        }
        if(euser.getText().toString().isEmpty())
        {
            euser.setError("please enter username");
        }
        if(epass.getText().toString().isEmpty())
        {
            epass.setError("please enter password");
        }

        if(!ename.getText().toString().matches("[a-zA-Z /s]+"))
        {
            cnt++;
            ename.setError("please enter valid name");
        }
        if(email.getText().toString().matches("[a-z][a-zA-Z0-9.#$!^&*()_<>;:']+@[a-z]+.[a-z]+")==false)
        {
            cnt++;
            email.setError("please enter valid email");
        }
        if(!emob.getText().toString().matches("[0-9]+"))
        {
            cnt++;
            emob.setError("please enter valid mobile no");
        }
        if(emob.getText().toString().length()!=10)
        {
            cnt++;
            emob.setError("mobile no must be 10 digits");
        }
        if(!euser.getText().toString().matches("[a-zA-Z0-9@.!#$%^&*()_+/.,:'; /s]+"))
        {
            cnt++;
            euser.setError("please enter valid username");
        }
        if(!epass.getText().toString().matches("[a-zA-Z0-9@.!#$%^&*()_+/.,:';]+"))
        {
            cnt++;
            epass.setError("please enter valid password");
        }
        if(cnt==0)
        {
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "please enter correct data", Toast.LENGTH_SHORT).show();
        }
    }
}