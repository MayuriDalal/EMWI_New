
package com.example.emwi_new.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class CountryModel {

    @Expose
    private int code;
    @Expose
    private ArrayList<Data> data;
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

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
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
