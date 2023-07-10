package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class AppointmentData {
    String treatment, date, status,medicines;
    float fees;
    int id, user_id, opd_id;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getOpd_id() {
        return opd_id;
    }

    public void setOpd_id(int opd_id) {
        this.opd_id = opd_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ArrayList<AppointmentData> collection = new ArrayList<AppointmentData>();
}
