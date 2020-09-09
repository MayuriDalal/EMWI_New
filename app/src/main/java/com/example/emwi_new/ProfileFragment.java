package com.example.emwi_new;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView tv_selected_tab;
    Button btn_info;
    Button btn_upload_KYC;
    Button btn_bank_details;
    Button btn_security;
    Button btn_contact_info;
    Button btn_my_dreams;
    LinearLayout ll_profil_info;
    LinearLayout ll_profil_upload_kyc;
    LinearLayout ll_profil_bank_details;
    LinearLayout ll_profil_security;
    LinearLayout ll_profil_contact_info;








    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tv_selected_tab = (TextView) view.findViewById(R.id.tv_selected_tab);
        btn_info=(Button)view.findViewById(R.id.btn_info);
        btn_info.setOnClickListener(this);
        btn_upload_KYC=(Button)view.findViewById(R.id.btn_upload_KYC);
        btn_upload_KYC.setOnClickListener(this);
        btn_bank_details=(Button)view.findViewById(R.id.btn_bank_details);
        btn_bank_details.setOnClickListener(this);
        btn_security=(Button)view.findViewById(R.id.btn_security);
        btn_security.setOnClickListener(this);
        btn_contact_info=(Button)view.findViewById(R.id.btn_contact_info);
        btn_contact_info.setOnClickListener(this);
        btn_my_dreams=(Button)view.findViewById(R.id.btn_my_dreams);
        btn_my_dreams.setOnClickListener(this);
        ll_profil_info=(LinearLayout)view.findViewById(R.id.ll_profil_info);
        ll_profil_info.setVisibility(View.GONE);
        ll_profil_upload_kyc=(LinearLayout)view.findViewById(R.id.ll_profil_upload_kyc);
        ll_profil_upload_kyc.setVisibility(View.GONE);
        ll_profil_bank_details=(LinearLayout)view.findViewById(R.id.ll_profil_bank_details);
        ll_profil_bank_details.setVisibility(View.GONE);
        ll_profil_security=(LinearLayout)view.findViewById(R.id.ll_profil_security);
        ll_profil_security.setVisibility(View.GONE);
        ll_profil_contact_info=(LinearLayout)view.findViewById(R.id.ll_profil_contact_info);
        ll_profil_contact_info.setVisibility(View.GONE);


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_info:{
                tv_selected_tab.setText("Info");
                ll_profil_info.setVisibility(View.VISIBLE);
                ll_profil_upload_kyc.setVisibility(View.GONE);
                ll_profil_bank_details.setVisibility(View.GONE);
                ll_profil_security.setVisibility(View.GONE);
                ll_profil_contact_info.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_upload_KYC:{
                tv_selected_tab.setText("Upload KYC");
                ll_profil_info.setVisibility(View.GONE);
                ll_profil_upload_kyc.setVisibility(View.VISIBLE);
                ll_profil_bank_details.setVisibility(View.GONE);
                ll_profil_security.setVisibility(View.GONE);
                ll_profil_contact_info.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_bank_details:{
                tv_selected_tab.setText("Bank Details");
                ll_profil_info.setVisibility(View.GONE);
                ll_profil_upload_kyc.setVisibility(View.GONE);
                ll_profil_bank_details.setVisibility(View.VISIBLE);
                ll_profil_security.setVisibility(View.GONE);
                ll_profil_contact_info.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_security:{
                tv_selected_tab.setText("Security");
                ll_profil_info.setVisibility(View.GONE);
                ll_profil_upload_kyc.setVisibility(View.GONE);
                ll_profil_bank_details.setVisibility(View.GONE);
                ll_profil_security.setVisibility(View.VISIBLE);
                ll_profil_contact_info.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_contact_info:{
                tv_selected_tab.setText("Contact Info");
                ll_profil_info.setVisibility(View.GONE);
                ll_profil_upload_kyc.setVisibility(View.GONE);
                ll_profil_bank_details.setVisibility(View.GONE);
                ll_profil_security.setVisibility(View.GONE);
                ll_profil_contact_info.setVisibility(View.VISIBLE);
                break;
            } case R.id.btn_my_dreams:{
                tv_selected_tab.setText("My Dreams");
                ll_profil_info.setVisibility(View.GONE);
                ll_profil_upload_kyc.setVisibility(View.GONE);
                ll_profil_bank_details.setVisibility(View.GONE);
                ll_profil_security.setVisibility(View.GONE);
                ll_profil_contact_info.setVisibility(View.GONE);
                break;
            }


        }
    }
}