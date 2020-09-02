package com.example.emwi_new.model.requestmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequestModel implements Serializable {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("password")
    private String password;

    public LoginRequestModel(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
}
