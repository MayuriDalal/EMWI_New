package com.example.emwi_new.auth;

import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.StateData;

import java.util.List;

public interface RegisterListener {
    void onSelectImage();
    void onSelectDob();
    void onCountrySuccess(List<Data> items);
    void onStateSuccess(List<StateData> items);
    void onCitySuccess(List<StateData> items);
    boolean onSendOtp();
    void onCheckUserSuccess(CheckUserDataModel dataModel);
    void onCheckPanSuccess(String message);
    void onApiError(String message);
}
