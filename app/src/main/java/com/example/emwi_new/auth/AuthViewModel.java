package com.example.emwi_new.auth;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    public String username;
    public String password;
    private String errorMessage = "Email or Password not valid";

    public AuthListener authListener;
    private LiveData<LoginResponseModel> loginResponse;

    public void onLoginButtonClick(View view){
        authListener.onStarted();
        if(isInputDataValid()){
            authListener.onFailure(errorMessage);
            return;
        }
        //success
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("user_id", username);
        loginMap.put("password", password);
        loginResponse = new UserRepository().userLogin(loginMap);
        authListener.onSuccess(loginResponse);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length() > 5;
    }
}
