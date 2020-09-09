package com.example.emwi_new.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthListener;
import com.example.emwi_new.databinding.FragmentDashboardBinding;
import com.example.emwi_new.model.DashboardModel;
import com.example.emwi_new.model.responsemodel.LoginResponseModel;
import com.example.emwi_new.utils.AppCommon;
import com.example.emwi_new.viewmodel.DashViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DashboardFragment extends Fragment implements AuthListener {

    private DashViewModel dashViewModel;
    FragmentDashboardBinding dashboardBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashViewModel =
                ViewModelProviders.of(this).get(DashViewModel.class);
        dashboardBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dashboard,container,false);
        View view = dashboardBinding.getRoot();
        dashViewModel.getDashboardData(getActivity());
        //dashboardBinding.setDashviewmodel(dashViewModel);
        dashboardBinding.setLifecycleOwner(this);
        //View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        /*dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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
                //dashViewModel = dashboardResponse.getValue().getData();
                //dashboardBinding.setDashviewmodel(dashViewModel);
                //AppCommon.setToken(loginResponse.getValue().getData());


            }
        });
    }

    @Override
    public void onFailure(String message) {

    }
}
