package com.example.emwi_new.model.responsemodel;

import com.example.emwi_new.model.Data;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class StateCityModel {
    @Expose
    private int code;
    @Expose
    private ArrayList<Datum> data;
    @Expose
    private String message;
    @Expose
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
