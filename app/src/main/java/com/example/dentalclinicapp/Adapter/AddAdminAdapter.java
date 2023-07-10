package com.example.dentalclinicapp.Adapter;

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
import com.example.dentalclinicapp.Utility.AdminData;
import com.example.dentalclinicapp.Utility.AdminListData;
import com.example.dentalclinicapp.Utility.PatientListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class AddAdminAdapter extends BaseAdapter {
    Context context;
    int pos;
    public AddAdminAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return AdminListData.collection.size();
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
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.edit_admin_layout,null);
        TextView uname=convertView.findViewById(R.id.txt_username);
        TextView name=convertView.findViewById(R.id.txt_name);
        ToggleButton btn_block_unblock = convertView.findViewById(R.id.btn_block_unblock);
        Button btn_delete = convertView.findViewById(R.id.btn_delete);
        uname.setText("Username: "+AdminListData.collection.get(position).getUsername());
        name.setText("Name: "+AdminListData.collection.get(position).getName());
        if (AdminListData.collection.get(position).getStatus().equalsIgnoreCase("active")){
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
                    block_admin();
                }
                else {
                    unblock_admin();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_admin();
            }
        });
        return convertView;
    }

    private void delete_admin() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.DELETE_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Admin status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void unblock_admin() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.UNBLOCK_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Admin status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void block_admin() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.BLOCK_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Admin status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+ AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }
}
