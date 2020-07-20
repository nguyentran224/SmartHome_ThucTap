package com.example.smarthome;

public class DataAirCon {
    public String aidi;
    public String tenmaylanh;

    public DataAirCon(String aidi, String tenmaylanh) {
        this.aidi = aidi;
        this.tenmaylanh = tenmaylanh;
    }

    public String getAidi() {
        return aidi;
    }

    public void setAidi(String aidi) {
        this.aidi = aidi;
    }

    public String getTenmaylanh() {
        return tenmaylanh;
    }

    public void setTenmaylanh(String tenmaylanh) {
        this.tenmaylanh = tenmaylanh;
    }
}
