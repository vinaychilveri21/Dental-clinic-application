package com.example.dentalclinicapp.Utility;

import java.util.ArrayList;

public class DoctorData {
    int id;
    String username,password,name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<DoctorData> collection = new ArrayList<DoctorData>();
}
