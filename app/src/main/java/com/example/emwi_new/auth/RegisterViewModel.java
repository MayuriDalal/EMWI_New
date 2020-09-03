package com.example.emwi_new.auth;

import android.view.View;

import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.repository.UserRepository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    public String fullname;
    public String email;
    public String user_id;
    public String password;
    public String confirm_password;
    public String ref_user_id;
    public String position;
    public String sponsor_name;
    public String mobile;
    public String dob;
    public String nominee_name;
    public String country;
    public String state;
    public String address;
    public String city;
    public String pincode;
    public String pan_no;
    public String aadhar_no;
    public File pan_photo;
    public File aadhar_photo;

    public RegisterListener registerListener;
    private LiveData<LoginResponseModel> sendOtpResponse;

    public void onSendOtpButtonClick(View view) {
        if(registerListener.onSendOtp()){
            //success
            Map<String, String> otpMap = new HashMap<>();
            otpMap.put("whatsapp_no", mobile);
            sendOtpResponse = new UserRepository().userSendOtp(otpMap);
        }
    }

    public void onSubmitButtonClick(View view) {
    }

    public void onDobSelectClick(View view) {
        registerListener.onSelectDob();
    }

    public void onPanSelectClick(View view) {
        registerListener.onSelectImage();
    }

    public void onAadharSelectClick(View view) {
        registerListener.onSelectImage();
    }
}
