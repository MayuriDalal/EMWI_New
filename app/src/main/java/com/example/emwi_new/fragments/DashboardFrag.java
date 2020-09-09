package com.example.emwi_new.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthListener;
import com.example.emwi_new.databinding.DashboardFragmentBinding;
import com.example.emwi_new.model.DashboardModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.utils.AppCommon;
import com.example.emwi_new.viewmodel.DashViewModel;

public class DashboardFrag extends Fragment implements AuthListener {

    private DashboardViewModel mViewModel;
    //FragmentD dashboardBinding;

    public static DashboardFrag newInstance() {
        return new DashboardFrag();
    }

    DashboardFragmentBinding dashboardBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        dashboardBinding = DataBindingUtil.inflate(inflater,R.layout.dashboard_fragment,container,false);
        View view = dashboardBinding.getRoot();
        mViewModel.getDashboardData(getActivity());
        dashboardBinding.setDashviewmodel(mViewModel);
        dashboardBinding.setLifecycleOwner(this);

        return view;
    }
    @Override
    public void onStarted() {

    }

    @Override
    public void onSignUp() {

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onSuccess(LiveData<LoginResponseModel> loginResponse) {

    }

    @Override
    public void onDashboardSuccess(LiveData<DashboardModel> dashboardResponse) {
        dashboardResponse.observe(this, new Observer<DashboardModel>() {
            @Override
            public void onChanged(DashboardModel dashResponseModel) {
                AppCommon.getInstance(getActivity());
                mViewModel = dashboardResponse.getValue().getData();
                dashboardBinding.setDashviewmodel(mViewModel);
                //AppCommon.setToken(loginResponse.getValue().getData());


            }
        });
    }

    @Override
    public void onFailure(String message) {

    }
}