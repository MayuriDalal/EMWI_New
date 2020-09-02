package com.example.emwi_new.retrofit;

import com.example.emwi_new.model.CountryStateCityModel;
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
    Call<CountryStateCityModel> GetCountries();

    @POST("user/profile")
    Call<CountryStateCityModel> updateUserProfile(@Body RequestBody post);

}
