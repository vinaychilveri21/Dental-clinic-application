package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class ServiceData {
    String service_name,service_status;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_status() {
        return service_status;
    }

    public void setService_status(String service_status) {
        this.service_status = service_status;
    }

    public static ArrayList<ServiceData> collection = new ArrayList<ServiceData>();
}
