
package com.example.emwi_new.model.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckUserDataModel {
    @Expose
    private int id;
    @Expose
    private String user_id;
    @Expose
    private String fullname;
    @Expose
    private String remember_token;

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRememberToken() {
        return remember_token;
    }

    public void setRememberToken(String remember_token) {
        this.remember_token = remember_token;
    }
}
