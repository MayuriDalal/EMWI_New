package com.example.emwi_new.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.emwi_new.ViewClasses.Activities.RegisterActivity;
import com.example.emwi_new.model.CountryStateCityModel;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.utils.AppCommon;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitUtils {

    public static List<Data> callCountryApi(Context context) {
        List<Data> data = new ArrayList<>();
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.GetCountries();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CountryStateCityModel countryResponse = (CountryStateCityModel) response.body();
                    if (countryResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(countryResponse));
                        if (countryResponse.getCode() == 200) {
                            data.addAll(countryResponse.getData());
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
        return data;
    }

    public static List<Data> callStateByCountryApi(Context context, String country) {
        List<Data> data = new ArrayList<>();
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.GetCountries();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CountryStateCityModel countryResponse = (CountryStateCityModel) response.body();
                    if (countryResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(countryResponse));
                        if (countryResponse.getCode() == 200) {
                            data.addAll(countryResponse.getData());
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
        return data;
    }
}
