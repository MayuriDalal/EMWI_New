package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emwi_new.auth.AuthViewModel;
import com.example.emwi_new.databinding.ActivityLoginBinding;
import android.os.Bundle;
import android.widget.Toast;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthListener;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.utils.ViewUtils;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        AuthViewModel viewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        loginBinding.setViewmodel(viewModel);
        viewModel.authListener = this;
        loginBinding.executePendingBindings();
        loginBinding.setLifecycleOwner(this);


        //setContentView(R.layout.activity_login);
    }

    @Override
    public void onStarted() {
        ViewUtils.showMessage(this,"Login Started");
    }

    @Override
    public void onSuccess(LiveData<LoginResponseModel> loginResponse) {
        loginResponse.observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(LoginResponseModel loginResponseModel) {

            }
        });
    }

    @Override
    public void onFailure(String message) {
        ViewUtils.showMessage(this,message);

    }
}