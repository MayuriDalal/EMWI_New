package com.example.emwi_new.fragments;

import android.content.Context;

import com.example.emwi_new.auth.AuthListener;
import com.example.emwi_new.model.DashboardModel;
import com.example.emwi_new.repository.UserRepository;
import com.google.gson.annotations.SerializedName;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    public int weekleftBV;
    public int weekrightBV;
    @SerializedName("DirectIncome")
    public int directIncome;
    @SerializedName("BinaryIncome")
    public int binaryIncome;
    @SerializedName("SelfrepurchaseBV")
    public int selfrepurchaseBV;
    @SerializedName("TotalIncome")
    public int totalIncome;
    @SerializedName("TotalWithdraw")
    public int totalWithdraw;
    @SerializedName("TotalProfit")
    public int totalProfit;
    @SerializedName("AwardIncome")
    public int awardIncome;
    @SerializedName("RoiIncome")
    public int roiIncome;
    @SerializedName("DirectRoiIncome")
    public int directRoiIncome;
    @SerializedName("BinaryRoiIncome")
    public int binaryRoiIncome;
    @SerializedName("RepurchaseIncome")
    public int repurchaseIncome;
    @SerializedName("RoyalityIncome")
    public int royalityIncome;
    @SerializedName("Designation")
    public String designation;
    @SerializedName("TotalDirect")
    public int totalDirect;
    public int left_bv;
    public int right_bv;
    public int left_bv_rep;
    public int right_bv_rep;

    public AuthListener authListener;
    private LiveData<DashboardModel> dashboardResponse;

    public void getDashboardData(Context context){
        dashboardResponse = new UserRepository().getUserDashboard(context);
        authListener.onDashboardSuccess(dashboardResponse);
    }
}