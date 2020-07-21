package com.example.smarthome;

public class DataLight {
    public String id;
    public String tentb;

    public DataLight(String id, String tentb) {
        this.id = id;
        this.tentb = tentb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTentb() {
        return tentb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }
}
