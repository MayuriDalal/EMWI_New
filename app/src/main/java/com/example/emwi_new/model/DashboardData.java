package com.example.emwi_new.model;

import com.google.gson.annotations.SerializedName;

public class DashboardData {

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
}
