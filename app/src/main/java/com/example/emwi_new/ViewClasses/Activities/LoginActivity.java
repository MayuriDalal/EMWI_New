package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emwi_new.auth.AuthViewModel;
import com.example.emwi_new.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthListener;
import com.example.emwi_new.model.DashboardModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.utils.AppCommon;
import com.example.emwi_new.utils.ViewUtils;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    TextView tv_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        AuthViewModel viewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        loginBinding.setViewmodel(viewModel);
        viewModel.authListener = this;
        loginBinding.executePendingBindings();
        loginBinding.setLifecycleOwner(this);
    }

    @Override
    public void onStarted() {
        ViewUtils.showMessage(this,"Login Started");
    }

    @Override
    public void onSignUp() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onSuccess(LiveData<LoginResponseModel> loginResponse) {
        loginResponse.observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(LoginResponseModel loginResponseModel) {
                //save access token
                AppCommon.getInstance(LoginActivity.this);
                AppCommon.setToken(loginResponse.getValue().getData().getAccessToken());
                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onDashboardSuccess(LiveData<DashboardModel> loginResponse) {

    }

    @Override
    public void onFailure(String message) {
        ViewUtils.showMessage(this,message);

    }
}