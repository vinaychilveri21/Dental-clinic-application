package com.example.dentalclinicapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.R;
import com.example.dentalclinicapp.Utility.DoctorAppointmentData;
import com.example.dentalclinicapp.Utility.PatientListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class PatientListAdapter extends BaseAdapter {
    int pos;

    Context context;
    public PatientListAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return PatientListData.collection.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.patient_list_layout,null);
        TextView txt_username,txt_name;
        Button btn_delete;
        ToggleButton btn_block_unblock;
        txt_username=view.findViewById(R.id.txt_username);
        txt_name=view.findViewById(R.id.txt_name);
        btn_delete = view.findViewById(R.id.btn_delete);
        btn_block_unblock = view.findViewById(R.id.btn_block_unblock);
        txt_username.setText("Username: "+PatientListData.collection.get(i).getUsername());
        txt_name.setText("Name: "+PatientListData.collection.get(i).getName());
        if (PatientListData.collection.get(i).getStatus().equalsIgnoreCase("active")){
            btn_block_unblock.setChecked(false);
        }
        else {
            btn_block_unblock.setChecked(true);
        }
        btn_block_unblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = i;
                if (btn_block_unblock.isChecked()){
                    block_user();
                }
                else {
                    unblock_user();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = i;
                delete_user();
            }
        });
        return view;
    }

    private void delete_user() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.DELETE_USER;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Patient record deleted successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ PatientListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void unblock_user() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.UNBLOCK_USER;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Patient status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ PatientListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void block_user() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.BLOCK_USER;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Patient status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ PatientListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }
}
