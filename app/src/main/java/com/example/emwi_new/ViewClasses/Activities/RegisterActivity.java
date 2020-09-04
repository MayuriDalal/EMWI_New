package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthViewModel;
import com.example.emwi_new.auth.RegisterListener;
import com.example.emwi_new.auth.RegisterViewModel;
import com.example.emwi_new.base.BaseActivity;
import com.example.emwi_new.databinding.ActivityRegisterBinding;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.model.responsemodel.StateData;
import com.example.emwi_new.retrofit.RetrofitUtils;
import com.example.emwi_new.utils.AppCommon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements RegisterListener, TextWatcher, View.OnFocusChangeListener,
        View.OnClickListener {

    EditText tv_fullname,tv_mobile,tv_email,tv_nominee_name,tv_sponsor_id,tv_password,tv_conf_password,tv_pan_card_no,
    tv_dob, tv_sponsor_name;
    TextView tv_country, tv_state, tv_city;
    String country = "IN";
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    RegisterViewModel viewModel;
    List<Data> countryList;
    List<StateData> stateList;
    List<StateData> cityList;
    String[] countryArray;
    String[] stateArray;
    String[] cityArray;
    String progressBarMessage = "Please wait...";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding registerBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerBinding.setViewmodel(viewModel);
        viewModel.registerListener = this;
        registerBinding.executePendingBindings();
        registerBinding.setLifecycleOwner(this);
        initViews();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        showProgressDialog(progressBarMessage);
        RetrofitUtils.callCountryApi(this,viewModel.registerListener);
    }

    private void initViews() {
        tv_fullname = (EditText)findViewById(R.id.tv_fullname);
        tv_mobile = (EditText)findViewById(R.id.tv_mobile);
        tv_email = (EditText)findViewById(R.id.tv_email);
        tv_nominee_name = (EditText)findViewById(R.id.tv_nominee_name);
        tv_sponsor_id = (EditText)findViewById(R.id.tv_sponsor_id);
        tv_password = (EditText)findViewById(R.id.tv_password);
        tv_conf_password = (EditText)findViewById(R.id.tv_conf_password);
        tv_pan_card_no = (EditText)findViewById(R.id.tv_pan_card_no);
        tv_dob = (EditText)findViewById(R.id.tv_dob);
        tv_sponsor_name = (EditText)findViewById(R.id.tv_sponsor_name);
        tv_country = (TextView) findViewById(R.id.tv_country);
        tv_state = (TextView)findViewById(R.id.tv_state);
        tv_city = (TextView)findViewById(R.id.tv_city);
        tv_mobile.addTextChangedListener(this);
        tv_country.setOnClickListener(this);
        tv_state.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        //tv_sponsor_id.addTextChangedListener(this);
        tv_sponsor_id.setOnFocusChangeListener(this);
    }

    @Override
    public void onSelectImage() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    @Override
    public void onSelectDob() {
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onCountrySuccess(List<Data> items) {
        try {
            countryList = new ArrayList<>();
            countryList.addAll(items);
            if (countryList.size() > 0) {
                countryArray = new String[countryList.size()];
                for (int i = 0; i < countryList.size(); i++) {
                    countryArray[i] = countryList.get(i).getCountry();
                    if (countryArray[i].equals("India")) {
                        tv_country.setText(countryArray[i]);
                        tv_state.setText("Select State");
                        showProgressDialog(progressBarMessage);
                        RetrofitUtils.callStateByCountryApi(this,countryList.get(i).getIsoCode(), viewModel.registerListener);
                    }
                }

            }
        }catch (Exception e){
            Log.e("ERROR",e.getLocalizedMessage());
        }finally {
            hideProgressDialog();
        }
    }

    @Override
    public void onStateSuccess(List<StateData> items) {
        try{
            stateList = new ArrayList<>();
            stateList.addAll(items);
            if (stateList.size() > 0) {
                stateArray = new String[stateList.size()];
                for (int i = 0; i < stateList.size(); i++) {
                    stateArray[i] = stateList.get(i).getName();
                        tv_state.setText("Select State");
                }
               // showProgressDialog(progressBarMessage);
                //RetrofitUtils.callCityByStateApi(this,tv_state.getText().toString(), viewModel.registerListener);
            }
        }catch (Exception e){
            Log.e("ERROR",e.getLocalizedMessage());
        }finally {
            hideProgressDialog();
        }
    }

    @Override
    public void onCitySuccess(List<StateData> items) {
        try{
            cityList = new ArrayList<>();
            cityList.addAll(items);
            if (cityList.size() > 0) {
                cityArray = new String[cityList.size()];
                for (int i = 0; i < cityList.size(); i++) {
                    cityArray[i] = cityList.get(i).getName();
                    tv_state.setText("Select State");
                }
            }
        }catch (Exception e){
            Log.e("ERROR",e.getLocalizedMessage());
        }finally {
            hideProgressDialog();
        }
    }

    @Override
    public boolean onSendOtp() {
        if(validation())
            return true;
        else
            return false;
    }

    @Override
    public void onCheckUserSuccess(CheckUserDataModel dataModel)
    {
        if(dataModel != null){
            tv_sponsor_name.setText(dataModel.getFullname());
        }
    }

    @Override
    public void onApiError(String message) {
        hideProgressDialog();
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    private boolean validation() {
        if(tv_fullname.getText().toString().isEmpty()){
            tv_fullname.setError(getText(R.string.err_full_name));
            tv_fullname.setFocusable(true);
            return false;
        }else if(tv_mobile.getText().toString().isEmpty() || tv_mobile.getText().toString().length() < 10){
            tv_mobile.setError(getText(R.string.err_mobile));
            tv_mobile.setFocusable(true);
            return false;
        }else if(AppCommon.isInputEmailValid(tv_email.getText().toString())){
            tv_email.setError(getText(R.string.err_email));
            tv_email.setFocusable(true);
            return false;
        }else if(tv_nominee_name.getText().toString().isEmpty()){
            tv_nominee_name.setError(getText(R.string.err_nominee_name));
            tv_nominee_name.setFocusable(true);
            return false;
        }else if(tv_sponsor_id.getText().toString().isEmpty()){
            tv_sponsor_id.setError(getText(R.string.err_sponsor_id));
            tv_sponsor_id.setFocusable(true);
            return false;
        }else if(tv_password.getText().toString().isEmpty()){
            tv_password.setError(getText(R.string.err_password));
            tv_password.setFocusable(true);
            return false;
        }else if(tv_password.getText().toString().length() < 6){
            tv_password.setError(getText(R.string.err_password_length));
            tv_password.setFocusable(true);
            return false;
        }else if(tv_conf_password.getText().toString().isEmpty()){
            tv_conf_password.setError(getText(R.string.err_conf_password));
            tv_conf_password.setFocusable(true);
            return false;
        }else if(tv_pan_card_no.getText().toString().isEmpty()){
            tv_pan_card_no.setError(getText(R.string.err_pan_no));
            tv_pan_card_no.setFocusable(true);
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                /*case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        //imageView.setImageBitmap(selectedImage);
                    }

                    break;*/
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditText dob = findViewById(R.id.tv_dob);
        dob.setText(sdf.format(myCalendar.getTime()));
        viewModel.dob = sdf.format(myCalendar.getTime());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if(charSequence.length() == 10){
            RetrofitUtils.callCheckMobileExistApi(this,tv_mobile.getText().toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(view.getId() == R.id.tv_sponsor_id) {
            RetrofitUtils.callCheckUserExistApi(this, tv_sponsor_id.getText().toString(), viewModel.registerListener);
        }
    }

    public void showSpinner(String[] items, String type){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a country");
        // add a list
        //String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(type.equals("country")) {
                    country = countryList.get(which).getIsoCode();
                    tv_country.setText(countryList.get(which).getCountry());
                    showProgressDialog(progressBarMessage);
                    RetrofitUtils.callStateByCountryApi(RegisterActivity.this, countryList.get(which).getIsoCode(), viewModel.registerListener);
                }else if(type.equals("state")){
                    tv_state.setText(stateList.get(which).getName());
                    showProgressDialog(progressBarMessage);
                    RetrofitUtils.callCityByStateApi(RegisterActivity.this, tv_state.getText().toString(), viewModel.registerListener);

                }else {
                    tv_city.setText(cityList.get(which).getName());
                }
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_country:
                if(countryList != null)
                    showSpinner(countryArray,"country");
                break;

            case R.id.tv_state:
                if(stateList != null)
                    showSpinner(stateArray,"state");
                break;

            case R.id.tv_city:
                if(cityList != null)
                    showSpinner(cityArray,"city");

        }
    }
}