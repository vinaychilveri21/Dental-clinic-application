package com.example.dentalclinicapp.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.R;
import com.example.dentalclinicapp.ShowTreatmentActivity;
import com.example.dentalclinicapp.Utility.AppointmentOpdData;
import com.example.dentalclinicapp.Utility.OpdServiceData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyAppointmentAdapter extends BaseAdapter {
    Context context;
    Calendar calendar;
    int y,m,d;
    String opd_id,appointment_date;

    public MyAppointmentAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return OpdServiceData.collection.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.my_appointment_layout,null);
        TextView treatmentName= convertView.findViewById(R.id.txt_treatment);
        TextView treatmentDate= convertView.findViewById(R.id.txt_date);
        treatmentName.setText("Treatment: "+OpdServiceData.collection.get(position).getService_name());
        treatmentDate.setText("Date: "+OpdServiceData.collection.get(position).getDatetime());
        Button btn_show = convertView.findViewById(R.id.btn_show_treatment);
        Button btn_request = convertView.findViewById(R.id.btn_request_treatment);
        calendar=Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opd_id = OpdServiceData.collection.get(position).getOpd_id()+"";
                String url = ProjectAPI.GET_USER_INDIVIDUAL_APPOINTMENT+"?opd_id="+opd_id;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                                AppointmentOpdData.collection.clear();
                                for (int i=1; i<response.length()-1;i++){
                                    AppointmentOpdData appointmentOpdData = new AppointmentOpdData();
                                    appointmentOpdData.setService_name(response.getJSONObject(i).getString("service_name"));
                                    appointmentOpdData.setTreatment_details(response.getJSONObject(i).getString("treatment_details"));
                                    appointmentOpdData.setMedicines(response.getJSONObject(i).getString("medicines"));
                                    appointmentOpdData.setFees(response.getJSONObject(i).getInt("fees"));
                                    appointmentOpdData.setDate(response.getJSONObject(i).getString("date"));
                                    AppointmentOpdData.collection.add(appointmentOpdData);
                                }
                                //Toast.makeText(context, "Appointment and OPD data added successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, ShowTreatmentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                            else {
                                Toast.makeText(context, response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(context, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }
        });
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        appointment_date = dayOfMonth+"/"+month+"/"+year;
                        opd_id = ""+ OpdServiceData.collection.get(position).getOpd_id();
                        request_appointment();
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        return convertView;
    }

    public void request_appointment(){
        String user_id = ""+UserData.collection.get(0).getId();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.APPOINTMENT_REQUEST;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Reappointment requested successfully", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "Appointment date: "+appointment_date, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id",user_id);
                params.put("opd_id",opd_id);
                params.put("date",appointment_date);
                return params;
            }
        };
        requestQueue.add(request);
    }

}
