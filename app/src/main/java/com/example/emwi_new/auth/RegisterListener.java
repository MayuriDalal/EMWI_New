package com.example.emwi_new.auth;

import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;

import java.util.List;

public interface RegisterListener {
    void onSelectImage();
    void onSelectDob();
    void onCountrySuccess(List<Data> items);
    boolean onSendOtp();
    void onCheckUserSuccess(CheckUserDataModel dataModel);
}
