package com.example.emwi_new.auth;

import com.example.emwi_new.model.responsemodel.CheckUserDataModel;

public interface RegisterListener {
    void onSelectImage();
    void onSelectDob();
    boolean onSendOtp();
    void onCheckUserSuccess(CheckUserDataModel dataModel);
}
