package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emwi_new.R;
import com.example.emwi_new.auth.AuthViewModel;
import com.example.emwi_new.auth.RegisterListener;
import com.example.emwi_new.auth.RegisterViewModel;
import com.example.emwi_new.databinding.ActivityLoginBinding;
import com.example.emwi_new.databinding.ActivityRegisterBinding;
import com.example.emwi_new.model.Data;
import com.example.emwi_new.model.responsemodel.CheckUserDataModel;
import com.example.emwi_new.retrofit.RetrofitUtils;
import com.example.emwi_new.utils.AppCommon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements RegisterListener, TextWatcher, View.OnFocusChangeListener {

    EditText tv_fullname,tv_mobile,tv_email,tv_nominee_name,tv_sponsor_id,tv_password,tv_conf_password,tv_pan_card_no,
    tv_dob, tv_sponsor_name;
    String country = "IN";
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    RegisterViewModel viewModel;
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

        List<Data> countryList = RetrofitUtils.callCountryApi(this);
        List<Data> stateList = RetrofitUtils.callStateByCountryApi(this,country);
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
        tv_mobile.addTextChangedListener(this);
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
}