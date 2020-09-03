package com.example.emwi_new.retrofit;

import com.example.emwi_new.model.CountryStateCityModel;
import com.example.emwi_new.model.responsemodel.CheckUserModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

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
    Call<CountryStateCityModel> getCountries();

    @POST("getStateByCountry")
    Call<CountryStateCityModel> getStateByCountry(@Body Map<String, String> post);

    @FormUrlEncoded
    @POST("send-registration-otp")
    Call<LoginResponseModel> sendOtp(@FieldMap Map<String, String> loginMap);

    @POST("checkmobileexist")
    Call<LoginResponseModel> checkMobileExist(@Body Map<String, String> mobileMap);

    @POST("checkuserexist")
    Call<CheckUserModel> checkUserExist(@Body Map<String, String> userMap);

}
