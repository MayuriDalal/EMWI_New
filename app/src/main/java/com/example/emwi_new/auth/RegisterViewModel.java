package com.example.emwi_new.auth;

import android.content.ContentResolver;
import android.view.View;

import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.repository.UserRepository;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    private LiveData<LoginResponseModel> verifyOtpResponse;
    private LiveData<LoginResponseModel> registerResponse;
    RequestBody panRequestFile,aadharRequestFile;

    public void onSendOtpButtonClick(View view) {
        String id = "MV" + String.valueOf(getRandomDoubleBetweenRange());
        user_id = id.substring(0,8).trim();
        if(registerListener.onSendOtp()){
            //success
            Map<String, String> otpMap = new HashMap<>();
            otpMap.put("whatsapp_no", mobile);
            sendOtpResponse = new UserRepository().userSendOtp(otpMap, registerListener);
        }
    }

    public void onSubmitButtonClick(View view) {
        String otp = registerListener.onCheckOtp();
        if(!otp.trim().equalsIgnoreCase("")) {
            //success
            Map<String, String> otpMap = new HashMap<>();
            otpMap.put("mobile", mobile);
            otpMap.put("otp", otp);
            //verifyOtpResponse = new UserRepository().userVerifyOtp(otpMap, registerListener,requestBody);
           // LoginResponseModel responseModel = verifyOtpResponse.getValue();
            //if (responseModel.getCode() == 200 && responseModel.getStatus().equalsIgnoreCase("OK")) {
                if (registerListener.onSubmitClick()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("fullname", fullname)
                            .addFormDataPart("email", email)
                            .addFormDataPart("user_id", user_id)
                            .addFormDataPart("password", password)
                            .addFormDataPart("confirm_password", confirm_password)
                            .addFormDataPart("ref_user_id", ref_user_id)
                            .addFormDataPart("position", position)
                            .addFormDataPart("sponsor_name", sponsor_name)
                            .addFormDataPart("mobile", mobile)
                            .addFormDataPart("dob", dob)
                            .addFormDataPart("nominee_name", nominee_name)
                            .addFormDataPart("country", country)
                            .addFormDataPart("state", state)
                            .addFormDataPart("address", address)
                            .addFormDataPart("city", city)
                            .addFormDataPart("pincode", pincode)
                            .addFormDataPart("pan_no", pan_no)
                            .addFormDataPart("aadhar_no", aadhar_no);
                    if (pan_photo != null) {
           /* RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(this.getContentResolver().getType(imageUri)),
                            pan_photo
                    );*/
                        builder.addFormDataPart("pan_photo", pan_photo.getName(), panRequestFile);
                    }
                    if (aadhar_photo != null) {
            /*RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(this.getContentResolver().getType(imageUri)),
                            aadhar_photo
                    );*/
                        builder.addFormDataPart("aadhar_photo", aadhar_photo.getName(), aadharRequestFile);
                    }

                    RequestBody requestBody = builder.build();
                    verifyOtpResponse = new UserRepository().userVerifyOtp(otpMap, registerListener,requestBody);
                    //registerResponse = new UserRepository().userRegister(requestBody, registerListener);
                }
            }
        //}
    }

    public void onDobSelectClick(View view) {
        registerListener.onSelectDob();
    }

    public void onPanSelectClick(View view) {
        panRequestFile = registerListener.onSelectImage("pan");
    }

    public void onAadharSelectClick(View view) {
        aadharRequestFile = registerListener.onSelectImage("aadhar");
    }

    public static double getRandomDoubleBetweenRange(){
        double x = (Math.random()*((999999-100000)+1))+100000;
        return x;
    }
}
