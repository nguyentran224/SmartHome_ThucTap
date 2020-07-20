package com.example.smarthome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Light {
    @Expose
    @SerializedName("number") private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
