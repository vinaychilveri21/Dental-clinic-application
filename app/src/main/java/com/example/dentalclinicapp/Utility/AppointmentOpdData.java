package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class AppointmentOpdData {
    String service_name,treatment_details,date,medicines;
    float fees;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getTreatment_details() {
        return treatment_details;
    }

    public void setTreatment_details(String treatment_details) {
        this.treatment_details = treatment_details;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public float getFees() {
        return fees;
    }
    public static ArrayList<AppointmentOpdData> collection = new ArrayList<AppointmentOpdData>();
}
