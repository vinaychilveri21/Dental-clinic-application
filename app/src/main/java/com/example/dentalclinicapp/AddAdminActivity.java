package com.example.dentalclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.AddAdminAdapter;
import com.example.dentalclinicapp.Utility.AdminListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import org.json.JSONArray;

public class AddAdminActivity extends AppCompatActivity {
    ListView lst_admin;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        add=findViewById(R.id.add_btn);
        lst_admin=findViewById(R.id.lst_admin);
        AddAdminAdapter adapter=new AddAdminAdapter(getApplicationContext());
        lst_admin.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAdminActivity.this,AdminRegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = ProjectAPI.GET_ADMINS;
        RequestQueue requestQueue = Volley.newRequestQueue(AddAdminActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        AdminListData.collection.clear();
                        for (int i=1; i<response.length()-1;i++){
                            AdminListData adminListData = new AdminListData();
                            adminListData.setId(response.getJSONObject(i).getInt("id"));
                            adminListData.setName(response.getJSONObject(i).getString("name"));
                            adminListData.setMobile(response.getJSONObject(i).getString("mobile"));
                            adminListData.setEmail(response.getJSONObject(i).getString("email"));
                            adminListData.setAddress(response.getJSONObject(i).getString("address"));
                            adminListData.setStatus(response.getJSONObject(i).getString("status"));
                            adminListData.setUsername(response.getJSONObject(i).getString("username"));
                            AdminListData.collection.add(adminListData);
                        }
                        //Toast.makeText(AddAdminActivity.this, "All Admin data fetched successfully", Toast.LENGTH_SHORT).show();

                        AddAdminAdapter adapter=new AddAdminAdapter(getApplicationContext());
                        lst_admin.setAdapter(adapter);
                    }
                }catch (Exception e){
                    Toast.makeText(AddAdminActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddAdminActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);

    }
}