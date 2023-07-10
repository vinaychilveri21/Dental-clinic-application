package com.example.dentalclinicapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalclinicapp.Adapter.PatientListAdapter;
import com.example.dentalclinicapp.Utility.PatientListData;
import com.example.dentalclinicapp.Utility.ProjectAPI;

import org.json.JSONArray;

public class PatientListActivity extends AppCompatActivity {

    ListView plst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        plst=findViewById(R.id.patient_lst);
        String url = ProjectAPI.GET_USERS;
        RequestQueue requestQueue = Volley.newRequestQueue(PatientListActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.getJSONObject(0).getString("status").equalsIgnoreCase("success")){
                        PatientListData.collection.clear();
                        for (int i=1; i<response.length()-1;i++){
                            PatientListData patientListData = new PatientListData();
                            patientListData.setId(response.getJSONObject(i).getInt("id"));
                            patientListData.setName(response.getJSONObject(i).getString("name"));
                            patientListData.setMobile(response.getJSONObject(i).getString("mobile"));
                            patientListData.setEmail(response.getJSONObject(i).getString("email"));
                            patientListData.setAddress(response.getJSONObject(i).getString("address"));
                            patientListData.setStatus(response.getJSONObject(i).getString("status"));
                            patientListData.setUsername(response.getJSONObject(i).getString("username"));
                            PatientListData.collection.add(patientListData);
                        }
                        PatientListAdapter adapter=new PatientListAdapter(getApplicationContext());
                        plst.setAdapter(adapter);
                        //Toast.makeText(PatientListActivity.this, "All Users data fetched successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(PatientListActivity.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PatientListActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);


    }
}