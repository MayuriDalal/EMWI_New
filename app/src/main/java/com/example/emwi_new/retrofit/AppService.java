package com.example.emwi_new.retrofit;

import com.example.emwi_new.model.CountryModel;
import com.example.emwi_new.model.responsemodel.CheckUserModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.model.responsemodel.StateCityModel;
import com.example.emwi_new.model.DashboardModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/*
 * Created by Tabish on 19-05-2020.
 */
public interface AppService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> userLogin(@FieldMap Map<String, String> loginMap);

    @GET("getCountry")
    Call<CountryModel> getCountries();

    @POST("getStateByCountry")
    Call<StateCityModel> getStateByCountry(@Body Map<String, String> post);

    @POST("getCityByState")
    Call<StateCityModel> getCityByState(@Body Map<String, String> post);

    @FormUrlEncoded
    @POST("send-registration-otp")
    Call<LoginResponseModel> sendOtp(@FieldMap Map<String, String> loginMap);

    @POST("checkmobileexist")
    Call<LoginResponseModel> checkMobileExist(@Body Map<String, String> mobileMap);

    @POST("checkpanexist")
    Call<LoginResponseModel> checkPanExist(@Body Map<String, String> panMap);

    @POST("checkuserexist")
    Call<CheckUserModel> checkUserExist(@Body Map<String, String> userMap);

    @GET("getAdminId")
    Call<CheckUserModel> getAdminId();

    @POST("register")
    Call<LoginResponseModel> userRegister(@Body RequestBody post);

    @GET("userDashboard")
    Call<DashboardModel> userDashboard();

}
