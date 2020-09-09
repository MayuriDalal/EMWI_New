package com.example.emwi_new.repository;

import android.content.Context;

import com.example.emwi_new.auth.RegisterListener;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.retrofit.AppService;
import com.example.emwi_new.retrofit.ServiceGenerator;
import com.example.emwi_new.utils.AppCommon;
import com.example.emwi_new.model.DashboardModel;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<LoginResponseModel> loginResponse;
    LoginResponseModel loginResponseModel;

    private MutableLiveData<DashboardModel> dashboardResponse;
    DashboardModel dashResponseModel;

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

    public LiveData<LoginResponseModel> userSendOtp(Map<String, String> sendOtpMap, RegisterListener listener){

        loginResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class);
        apiService.sendOtp(sendOtpMap).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    listener.onOtpSuccess();
                }else {
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    listener.onApiError(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                listener.onApiError(t.getLocalizedMessage());
                //Toast.makeText(UserRepository.this.getClass(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
        return loginResponse;
    }

    public LiveData<LoginResponseModel> userVerifyOtp(Map<String, String> sendOtpMap, RegisterListener listener,RequestBody registerMap){

        loginResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class);
        apiService.sendOtp(sendOtpMap).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    userRegister(registerMap, listener);
                    listener.onVerifyOtpSuccess(loginResponse);
                }else {
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    listener.onApiError(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                listener.onApiError(t.getLocalizedMessage());
                //Toast.makeText(UserRepository.this.getClass(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
        return loginResponse;
    }

    public LiveData<LoginResponseModel> userRegister(RequestBody registerMap, RegisterListener listener){

        loginResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class);
        apiService.userRegister(registerMap).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(response.isSuccessful()){
                    loginResponseModel = response.body();
                    loginResponse.setValue(loginResponseModel);
                    listener.onRegisterSuccess(loginResponseModel);
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

    public LiveData<DashboardModel> getUserDashboard(Context context){

        dashboardResponse = new MutableLiveData<>();

        AppService apiService = ServiceGenerator.createService(AppService.class,AppCommon.getInstance(context).getToken());
        apiService.userDashboard().enqueue(new Callback<DashboardModel>() {
            @Override
            public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                if(response.isSuccessful()){
                    dashResponseModel = response.body();
                    dashboardResponse.setValue(dashResponseModel);
                    // todo: save access token is pending
                }else {
                    dashResponseModel = response.body();
                    dashboardResponse.setValue(dashResponseModel);
                }
            }

            @Override
            public void onFailure(Call<DashboardModel> call, Throwable t) {
                //Toast.makeText(UserRepository.this.getClass(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
        return dashboardResponse;
    }
}
