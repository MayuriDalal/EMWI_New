package com.example.emwi_new.model;

import com.example.emwi_new.fragments.DashboardViewModel;
import com.example.emwi_new.viewmodel.DashViewModel;

public class DashboardModel {
    public long code;
    public String status;
    public String message;
    public DashboardViewModel data;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public DashboardViewModel getData() {
        return data;
    }

    public void setData(DashboardViewModel data) {
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
