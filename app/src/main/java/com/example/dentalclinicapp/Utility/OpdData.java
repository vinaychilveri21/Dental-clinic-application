package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class OpdData {
    int id, user_id, service_id;
    String datetime;

    public int getUser_id() {
        return user_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public static ArrayList<OpdData> collection = new ArrayList<OpdData>();
}
