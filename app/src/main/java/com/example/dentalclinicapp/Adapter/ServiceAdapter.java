package com.example.dentalclinicapp.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.example.dentalclinicapp.ServiceActivity;
import com.example.dentalclinicapp.Utility.OpdData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.ServiceData;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ServiceAdapter extends BaseAdapter {
    Context context;
    Calendar calendar;
    int y,m,d;
    public static int opd_ser_id;
    String opd_id,user_id,datetime;

    public ServiceAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return ServiceData.collection.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void opd_update(){
        int user_id = UserData.collection.get(0).getId();
        String url = ProjectAPI.GET_OPD+"?user_id="+user_id;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                        //Toast.makeText(context, "OPD Updated successfully", Toast.LENGTH_SHORT).show();
                        appointment();
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

    public void appointment(){

        user_id = ""+UserData.collection.get(0).getId();
        for(int i=0; i<OpdData.collection.size(); i++){
            //Toast.makeText(context, OpdData.collection.get(i).getService_id()+"", Toast.LENGTH_SHORT).show();
            if (OpdData.collection.get(i).getService_id() == opd_ser_id){
                opd_id = OpdData.collection.get(i).getId()+"";
            }
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.APPOINTMENT_REQUEST;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    ServiceActivity.pb.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Appointment requested success", Toast.LENGTH_SHORT).show();
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
                params.put("date",datetime);
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.service_list_layout,null);
        TextView txt_service= convertView.findViewById(R.id.txt_service);
        txt_service.setText(ServiceData.collection.get(position).getService_name());
        Button makeappointment = convertView.findViewById(R.id.btn_make_app);
        calendar=Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);

        makeappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Toast.makeText(context, dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
                        //String user_id = ""+UserData.collection.get(0).getId();
                        String user_id = UserData.collection.get(0).getId()+"";
                        opd_ser_id=ServiceData.collection.get(position).getId();
                        String service_id = ""+opd_ser_id;
                        datetime = dayOfMonth+"/"+(month+1)+"/"+year;
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        String url = ProjectAPI.OPD_REQUEST;
                        StringRequest opd_request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equalsIgnoreCase("success")){
                                    //Toast.makeText(context, "Request confirmed", Toast.LENGTH_SHORT).show();
                                    opd_update();
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
                                params.put("service_id",service_id);
                                params.put("date",datetime);
                                return params;
                            }
                        };
                        requestQueue.add(opd_request);
                        ServiceActivity.pb.setVisibility(View.VISIBLE);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        return convertView;
    }
}
