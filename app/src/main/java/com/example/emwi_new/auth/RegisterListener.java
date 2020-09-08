package com.example.emwi_new.auth;

import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.model.responsemodel.StateData;

import java.io.File;
import java.util.List;

import androidx.lifecycle.LiveData;
import okhttp3.RequestBody;

public interface RegisterListener {
    RequestBody onSelectImage(String type);
    void onSelectDob();
    void onCountrySuccess(List<Data> items);
    void onStateSuccess(List<StateData> items);
    void onCitySuccess(List<StateData> items);
    boolean onSendOtp();
    boolean onSubmitClick();
    void onOtpSuccess();
    String onCheckOtp();
    void onVerifyOtpSuccess(LiveData<LoginResponseModel> loginResponse);
    void onRegisterSuccess(LoginResponseModel responseModel);
    void onCheckUserSuccess(CheckUserDataModel dataModel);
    void onGetAdminIdSuccess(CheckUserDataModel dataModel);
    void onCheckPanSuccess(String message);
    void onApiError(String message);
}
