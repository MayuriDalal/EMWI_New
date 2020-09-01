package com.example.emwi_new.auth;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    public String username;
    public String password;

    public void onLoginButtonClick(View view){
        if(username.isEmpty() || password.isEmpty()){
            return;
        }
    }
}
