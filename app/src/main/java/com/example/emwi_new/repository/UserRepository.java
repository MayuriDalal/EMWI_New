package com.example.emwi_new.repository;

import android.widget.Toast;

import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.retrofit.AppService;
import com.example.emwi_new.retrofit.ServiceGenerator;
import com.example.emwi_new.utils.AppCommon;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<LoginResponseModel> loginResponse;
    LoginResponseModel loginResponseModel;

    public LiveData<LoginResponseModel> userLogin(Map<String, String> loginMap){

        loginResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class);
        apiService.userLogin(loginMap).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    // todo: save access token is pending
                }else {
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                //Toast.makeText(UserRepository.this.getClass(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
        return loginResponse;
    }

    public LiveData<LoginResponseModel> userSendOtp(Map<String, String> sendOtpMap){

        loginResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class);
        apiService.sendOtp(sendOtpMap).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    // todo: save access token is pending
                }else {
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                //Toast.makeText(UserRepository.this.getClass(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
        return loginResponse;
    }
}
