package com.example.dentalclinicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalclinicapp.R;
import com.example.dentalclinicapp.Utility.AppointmentOpdData;

public class TreatmentDetailsAdapter extends BaseAdapter {

    Context context;
    public TreatmentDetailsAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return AppointmentOpdData.collection.size();
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
        convertView = inflater.inflate(R.layout.appointment_details_list_layout,null);
        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_treatment = convertView.findViewById(R.id.txt_treatment);
        TextView txt_fees = convertView.findViewById(R.id.txt_fees);
        TextView txt_date = convertView.findViewById(R.id.txt_date);
        TextView txt_medicine = convertView.findViewById(R.id.txt_medicines);
        txt_name.setText("Treatment: "+AppointmentOpdData.collection.get(position).getService_name());
        txt_treatment.setText("Treatment Details:\n"+AppointmentOpdData.collection.get(position).getTreatment_details());
        txt_medicine.setText("Medicines:\n"+AppointmentOpdData.collection.get(position).getMedicines());
        txt_fees.setText("Fees: "+AppointmentOpdData.collection.get(position).getFees()+"");
        txt_date.setText("Date: "+AppointmentOpdData.collection.get(position).getDate());
        return convertView;
    }
}
