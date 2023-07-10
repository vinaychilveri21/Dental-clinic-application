package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class AboutUsData {
    int id;
    String details;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static ArrayList<AboutUsData> collection = new ArrayList<AboutUsData>();
}
