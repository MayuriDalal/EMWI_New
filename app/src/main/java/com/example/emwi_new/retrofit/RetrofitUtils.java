package com.example.emwi_new.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.emwi_new.ViewClasses.Activities.RegisterActivity;
import com.example.emwi_new.auth.RegisterListener;
import com.example.emwi_new.model.CountryStateCityModel;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.CheckUserModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.utils.AppCommon;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitUtils {

    public static void callCountryApi(Context context, RegisterListener listener) {
        List<Data> countryData = new ArrayList<>();
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.getCountries();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CountryStateCityModel countryResponse = (CountryStateCityModel) response.body();
                    if (countryResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(countryResponse));
                        if (countryResponse.getCode() == 200) {
                            countryData.addAll(countryResponse.getData());
                            listener.onCountrySuccess(countryData);
                        } else {
                            Toast.makeText(context, countryResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dismissProgressBar();
                    //AppCommon.getInstance(context.clearNonTouchableFlags(JoinMegaContest.this);
                    // loaderView.setVisibility(View.GONE);
                    //Toast.makeText(JoinMegaContest.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            //dismissProgressBar();
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    public static List<Data> callStateByCountryApi(Context context, String country) {
        List<Data> stateData = new ArrayList<>();
        Map<String, String> stateMap = new HashMap<>();
        stateMap.put("country", country);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.getStateByCountry(stateMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CountryStateCityModel stateResponse = (CountryStateCityModel) response.body();
                    if (stateResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(stateResponse));
                        if (stateResponse.getCode() == 200) {
                            stateData.addAll(stateResponse.getData());
                        } else {
                            Toast.makeText(context, stateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dismissProgressBar();
                    //AppCommon.getInstance(context.clearNonTouchableFlags(JoinMegaContest.this);
                    // loaderView.setVisibility(View.GONE);
                    //Toast.makeText(JoinMegaContest.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            //dismissProgressBar();
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
        return stateData;
    }

    public static void callCheckMobileExistApi(Context context, String mobile) {
        Map<String, String> stateMap = new HashMap<>();
        stateMap.put("mobile", mobile);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.checkMobileExist(stateMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    LoginResponseModel mobileResponse = (LoginResponseModel) response.body();
                    if (mobileResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(mobileResponse));
                        if (mobileResponse.getCode() == 200) {
                            Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dismissProgressBar();
                    //AppCommon.getInstance(context.clearNonTouchableFlags(JoinMegaContest.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            //dismissProgressBar();
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    public static void callCheckUserExistApi(Context context, String user_id, RegisterListener listener) {
        Map<String, String> stateMap = new HashMap<>();
        stateMap.put("user_id", user_id);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.checkUserExist(stateMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CheckUserModel mobileResponse = (CheckUserModel) response.body();
                    if (mobileResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(mobileResponse));
                        if (mobileResponse.getCode() == 200) {
                            listener.onCheckUserSuccess(mobileResponse.getData());
                            Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, mobileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dismissProgressBar();
                    //AppCommon.getInstance(context.clearNonTouchableFlags(JoinMegaContest.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            //dismissProgressBar();
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}
