package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class DoctorAppointmentData {
    String username,treatment,date,status,service_name;
    float fees;
    int opd_id,id,user_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_name() {
        return service_name;
    }


    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public int getOpd_id() {
        return opd_id;
    }

    public void setOpd_id(int opd_id) {
        this.opd_id = opd_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<DoctorAppointmentData> collection=new ArrayList<DoctorAppointmentData>();
}
