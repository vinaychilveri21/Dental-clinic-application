package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class OpdServiceData {
    int opd_id;
    String service_name,datetime;

    public void setOpd_id(int opd_id) {
        this.opd_id = opd_id;
    }

    public int getOpd_id() {
        return opd_id;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public static ArrayList<OpdServiceData> collection = new ArrayList<OpdServiceData>();
}