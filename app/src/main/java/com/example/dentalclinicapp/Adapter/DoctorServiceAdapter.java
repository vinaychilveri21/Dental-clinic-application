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
import com.example.dentalclinicapp.R;
import com.example.dentalclinicapp.Utility.OpdData;
import com.example.dentalclinicapp.Utility.PatientListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;
import com.example.dentalclinicapp.Utility.ServiceData;
import com.example.dentalclinicapp.Utility.UserData;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DoctorServiceAdapter extends BaseAdapter {
    Context context;
    Calendar calendar;
    int y,m,d;
    public static int opd_ser_id;
    String opd_id,user_id,datetime;
    int pos;

    public DoctorServiceAdapter(Context context){
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.doctor_service_list_layout,null);
        TextView txt_service= convertView.findViewById(R.id.txt_service);
        txt_service.setText(ServiceData.collection.get(position).getService_name());
        ToggleButton btn_block_unblock = convertView.findViewById(R.id.btn_block_unblock);
        Button btn_delete = convertView.findViewById(R.id.btn_delete);
        if (ServiceData.collection.get(position).getService_status().equalsIgnoreCase("active")){
            btn_block_unblock.setChecked(false);
        }
        else {
            btn_block_unblock.setChecked(true);
        }
        btn_block_unblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                if (btn_block_unblock.isChecked()){
                    block();
                }
                else {
                    unblock();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        return convertView;
    }

    private void unblock() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.UNBLOCK_SERVICE;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Service status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ ServiceData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void block() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.BLOCK_SERVICE;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Service status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ ServiceData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void delete() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.DELETE_SERVICE;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Service deleted successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ ServiceData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }
}
