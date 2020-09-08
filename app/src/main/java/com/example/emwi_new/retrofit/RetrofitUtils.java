package com.example.emwi_new.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.emwi_new.auth.RegisterListener;
import com.example.emwi_new.model.CountryModel;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.CheckUserModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.model.responsemodel.StateCityModel;
import com.example.emwi_new.model.responsemodel.StateData;
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
                    CountryModel countryResponse = (CountryModel) response.body();
                    if (countryResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(countryResponse));
                        if (countryResponse.getCode() == 200) {
                            countryData.addAll(countryResponse.getData());
                            listener.onCountrySuccess(countryData);
                        } else {
                            Toast.makeText(context, countryResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        listener.onApiError(response.message());
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    listener.onApiError(t.getLocalizedMessage());
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

    public static void callStateByCountryApi(Context context, String country, RegisterListener listener) {
        List<StateData> stateData = new ArrayList<>();
        Map<String, String> stateMap = new HashMap<>();
        stateMap.put("country", country);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.getStateByCountry(stateMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    StateCityModel stateResponse = (StateCityModel) response.body();
                    if (stateResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(stateResponse));
                        if (stateResponse.getCode() == 200) {
                            stateData.addAll(stateResponse.getData());
                            listener.onStateSuccess(stateData);
                        } else {
                            Toast.makeText(context, stateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        listener.onApiError(response.message());
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    listener.onApiError(t.getLocalizedMessage());
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

    public static void callCityByStateApi(Context context, String state, RegisterListener listener) {
        List<StateData> cityData = new ArrayList<>();
        Map<String, String> cityMap = new HashMap<>();
        cityMap.put("state", state);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.getCityByState(cityMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    StateCityModel cityResponse = (StateCityModel) response.body();
                    if (cityResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(cityResponse));
                        if (cityResponse.getCode() == 200) {
                            cityData.addAll(cityResponse.getData());
                            listener.onCitySuccess(cityData);
                        } else {
                            Toast.makeText(context, cityResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        listener.onApiError(response.message());
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    listener.onApiError(t.getLocalizedMessage());
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

    public static void callPanExistApi(Context context, String pan, RegisterListener listener) {
        Map<String, String> panMap = new HashMap<>();
        panMap.put("pan", pan);
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.checkPanExist(panMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    LoginResponseModel panResponse = (LoginResponseModel) response.body();
                    if (panResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(panResponse));
                        if (panResponse.getCode() == 200) {
                            listener.onCheckPanSuccess(panResponse.getMessage());
                            Toast.makeText(context, panResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, panResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, panResponse.getMessage(), Toast.LENGTH_SHORT).show();
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

    public static void callGetAdminId(Context context, RegisterListener listener) {
        if (AppCommon.getInstance(context).isConnectingToInternet(context)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            //dismissProgressBar();
            Call call = apiService.getAdminId();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    CheckUserModel getAdminResponse = (CheckUserModel) response.body();
                    if (getAdminResponse != null) {
                        Log.i("AuthResponse::", new Gson().toJson(getAdminResponse));
                        if (getAdminResponse.getCode() == 200) {
                            listener.onGetAdminIdSuccess(getAdminResponse.getData());
                        } else {
                            Toast.makeText(context, getAdminResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        listener.onApiError(response.message());
                        //AppCommon.getInstance(context).showDialog(JoinMegaContest.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    listener.onApiError(t.getLocalizedMessage());
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
}
