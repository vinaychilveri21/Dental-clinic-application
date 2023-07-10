package com.example.dentalclinicapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.dentalclinicapp.Adapter.DoctorAppointmentAdapter;
import com.example.dentalclinicapp.Utility.DoctorAppointmentData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddTreatmentDetailsActivity extends AppCompatActivity {

    EditText edt_treatment,edt_fees,edt_medicine;
    Calendar calendar;
    Button btn_save;
    int d,m,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment_details);
        edt_medicine = findViewById(R.id.edt_medicine);
        edt_treatment=findViewById(R.id.edt_treatment_name);
        edt_fees=findViewById(R.id.edt_fees);
        btn_save=findViewById(R.id.btn_save);
        calendar=Calendar.getInstance();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_details();
            }
        });
    }

    private void update_details() {
        boolean flag=true;
        if (edt_treatment.getText().toString().isEmpty()){
            edt_treatment.setError("Please enter treatment");
            flag=false;
        }
        if (edt_fees.getText().toString().isEmpty()){
            edt_fees.setError("Please enter fees");
            flag=false;
        }
        if(flag == true){
            RequestQueue requestQueue = Volley.newRequestQueue(AddTreatmentDetailsActivity.this);
            String url = ProjectAPI.ACCEPT_APPOINTMENT;
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("success")){
                        Toast.makeText(AddTreatmentDetailsActivity.this, "Status updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AddTreatmentDetailsActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddTreatmentDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @org.jetbrains.annotations.Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> param = new HashMap<String, String>();
                    param.put("id",""+ DoctorAppointmentData.collection.get(DoctorAppointmentAdapter.appointment_id).getId());
                    param.put("treatment",edt_treatment.getText().toString());
                    param.put("medicines",edt_medicine.getText().toString());
                    param.put("fees",edt_fees.getText().toString());
                    return param;
                }
            };
            requestQueue.add(request);
        }
    }

}