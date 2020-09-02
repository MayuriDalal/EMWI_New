package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.emwi_new.R;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.retrofit.RetrofitUtils;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        List<Data> countryList = RetrofitUtils.callCountryApi(this);
    }
}