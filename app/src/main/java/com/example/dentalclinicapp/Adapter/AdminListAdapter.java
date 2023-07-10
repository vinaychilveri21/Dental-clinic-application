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
import com.example.dentalclinicapp.Utility.AdminListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import java.util.HashMap;
import java.util.Map;

public class AdminListAdapter extends BaseAdapter {
    int pos;
    Context context;
    public AdminListAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return AdminListData.collection.size();
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
        view=inflater.inflate(R.layout.edit_admin_layout,null);
        TextView txt_username,txt_name;
        Button btn_delete;
        ToggleButton tb_block;
        txt_username=view.findViewById(R.id.txt_name);
        txt_name=view.findViewById(R.id.txt_email);
        tb_block=view.findViewById(R.id.toggle);
        btn_delete = view.findViewById(R.id.btn_delete);
        txt_username.setText("Username: "+AdminListData.collection.get(i).getUsername());
        txt_name.setText("Name: "+AdminListData.collection.get(i).getName());
        tb_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = i;
                if (tb_block.isChecked()){
                    block();
                }
                else {
                    unblock();
                }
                Toast.makeText(context,tb_block.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_admin();
            }
        });
        return view;
    }

    private void unblock() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.BLOCK_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Status updated to unblock successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void block() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.UNBLOCK_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Status updated successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }

    private void delete_admin() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = ProjectAPI.DELETE_ADMIN;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(context, "Admin delete successfully", Toast.LENGTH_SHORT).show();
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
                param.put("id",""+AdminListData.collection.get(pos).getId());
                return param;
            }
        };
        requestQueue.add(request);
    }
}
