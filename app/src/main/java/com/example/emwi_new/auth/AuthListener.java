package com.example.emwi_new.auth;

import com.example.emwi_new.model.DashboardModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;

import androidx.lifecycle.LiveData;

public interface AuthListener {

     void onStarted();
     void onSignUp();
     void onLogin();
     void onSuccess(LiveData<LoginResponseModel> loginResponse);
     void onDashboardSuccess(LiveData<DashboardModel> loginResponse);
     void onFailure(String message);
}
