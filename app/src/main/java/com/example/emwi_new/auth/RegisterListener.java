package com.example.emwi_new.auth;

import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.Datum;

import java.util.List;

public interface RegisterListener {
    void onSelectImage();
    void onSelectDob();
    void onCountrySuccess(List<Data> items);
    void onStateSuccess(List<Datum> items);
    void onCitySuccess(List<Datum> items);
    boolean onSendOtp();
    void onCheckUserSuccess(CheckUserDataModel dataModel);
    void onCheckPanSuccess(String message);
    void onApiError(String message);
}
