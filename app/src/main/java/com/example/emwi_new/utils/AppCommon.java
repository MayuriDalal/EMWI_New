package com.example.emwi_new.utils;

/*
 * Created by Tabish on 06-05-2020.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;


import com.example.emwi_new.R;

import static android.content.Context.MODE_PRIVATE;

public class AppCommon {

    public static AppCommon mInstance = null;

    static Context mContext;
    MediaPlayer mediaPlayer = null;
    public static ProgressBar progressBar;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    public static final int ENABLE_LOCATION_SERVICES_REQUEST_CODE = 205;

    public static AppCommon getInstance(Context _Context) {
        if (mInstance == null) {
            mInstance = new AppCommon();
        }
        mContext = _Context;
        return mInstance;
    }

    public void setNonTouchableFlags(Activity activity) {
        if (activity != null) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void clearNonTouchableFlags(Activity mActivity) {
        if (mActivity != null) {
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public void setUserLogin(String userId, boolean b) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putBoolean(MyPreference.login, b);
        editor.putString(MyPreference.userId, userId);
        editor.apply();
    }

    public boolean isUserLogIn() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getBoolean(MyPreference.login, false);
    }

    public String getPassword() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.password, "");
    }
    public void setPassword(String password) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.password, password);
        editor.apply();
    }

    public String getName() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.name, "");
    }
    public void setName(String name) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.name, name);
        editor.apply();
    }

    public void onHideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public String getUserId() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.userId, null);
    }

    public void setUserObject(String json) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.userObject, json);
        editor.apply();
    }

    public String getUserObject() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.userObject, null);
    }

    public void clearPreference() {
        SharedPreferences.Editor editorLogin = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editorLogin.clear();
        editorLogin.apply();

    }

    public void showDialog(Activity mactivity, String title) {
        if (!mactivity.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
            builder.setCancelable(false);
            builder.setTitle(mactivity.getResources().getString(R.string.app_name));
            builder.setMessage(title);
            builder.setCancelable(false);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    mactivity.finish();
                }
            });
            builder.show();
        }
    }

    public int getAccount() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getInt(MyPreference.bidCount, 0);
    }

    public void setAccount(int count) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putInt(MyPreference.bidCount, count);
        editor.apply();
    }

    public int getSesstionId() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getInt(MyPreference.SeetionId, 0);
    }

    public void setSesstionId(int count) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putInt(MyPreference.SeetionId, count);
        editor.apply();
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.TokenValue, token);
        editor.apply();
    }


    public void setUID(String token) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.TokenValue, token);
        editor.apply();
    }

    public void setAddress(String token, String prizm) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.BTCAddress, token);
        editor.putString(MyPreference.PrizmAddress, prizm);
        editor.apply();

    }

    public String getAddress() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.BTCAddress, "");
    }

    public String getPrizmAddress() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.PrizmAddress, "");
    }

    public static String getToken() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.TokenValue, "");
    }

   /* public DraweeController getDraweeController(SimpleDraweeView mImageView, String vendorImg, int i) {
        Uri uri = Uri.parse(vendorImg);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(i, i))
                .setProgressiveRenderingEnabled(false)
                .build();

        return Fresco.newDraweeControllerBuilder()
                .setOldController(mImageView.getController())
                .setImageRequest(request)
                .build();
    }
*/

   public void setID(Integer id) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putInt(MyPreference.ID, id);
        editor.apply();
    }

    public int  getID(){
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getInt(MyPreference.ID, 0);
    }

    public void IslangSelected(boolean flag) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putBoolean(MyPreference.PREFS_LANGUAGE_IS_SELECTED, flag);
        editor.apply();
    }
    public boolean  GetIsLangSelected(){
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getBoolean(MyPreference.PREFS_LANGUAGE_IS_SELECTED, false);
    }


    public void setSeleectedLan(String flag) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.SELECTEDlANG, flag);
        editor.apply();
    }
    public String GetLangSelected(){
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.SELECTEDlANG, "");
    }
    public void setGameToken(String flag) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.GameToken, flag);
        editor.apply();
    }
    public String getGameToken(){
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.GameToken, "");
    }

    public static boolean isInputEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() ;
    }

    public static void showProgressBar(Context context, boolean isCancellable, String message){
       progressBar = new ProgressBar(context);
       progressBar.setIndeterminate(true);
       progressBar.setVisibility(View.VISIBLE);
    }
}
