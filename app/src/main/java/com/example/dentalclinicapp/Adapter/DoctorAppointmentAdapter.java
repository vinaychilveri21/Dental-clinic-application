package com.example.dentalclinicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.AddTreatmentDetailsActivity;
import com.example.dentalclinicapp.DoctorAppointmentListActivity;
import com.example.dentalclinicapp.R;
import com.example.dentalclinicapp.Utility.AdminListData;
import com.example.dentalclinicapp.Utility.DoctorAppointmentData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class DoctorAppointmentAdapter extends BaseAdapter {
    Context context;
    int pos;
    public static int appointment_id;

    public DoctorAppointmentAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return DoctorAppointmentData.collection.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.doctor_appintment_list_layout,null);
        TextView txt_username,txt_service,txt_date;
        txt_username=view.findViewById(R.id.txt_username);
        txt_service=view.findViewById(R.id.txt_service);
        txt_date=view.findViewById(R.id.txt_date);

        txt_username.setText("Username: "+DoctorAppointmentData.collection.get(i).getUsername());
        txt_service.setText("Service name: "+DoctorAppointmentData.collection.get(i).getService_name());
        txt_date.setText("Date: "+DoctorAppointmentData.collection.get(i).getDate());

        Button btn_details=view.findViewById(R.id.btn_details);
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(context, AddTreatmentDetailsActivity.class);
                inte.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(inte);
                appointment_id = i;
            }
        });
        ToggleButton accept_decline = view.findViewById(R.id.appointment_status);
        accept_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos= i;
                if (accept_decline.isChecked()){
                    decline();
                }
                /*String url = ProjectAPI.GET_APPOINTMENT_LIST;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                                //Toast.makeText(context, "Appointment Data fetched successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Server message: "+response.getJSONObject(1).getString("msg"), Toast.LENGTH_SHORT).show();
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
                requestQueue.add(request);*/
            }
        });

        return view;
    }

    private void decline() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.DECLINE_APPOINTMENT;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Status updated successfully", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("id",""+ DoctorAppointmentData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }
}
