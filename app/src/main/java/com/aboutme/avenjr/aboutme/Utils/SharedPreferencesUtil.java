package com.aboutme.avenjr.aboutme.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.aboutme.avenjr.aboutme.R;

public class SharedPreferencesUtil {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Context context;

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
    }

    public void setName(String user) {
        editor.putString(context.getString(R.string.user_name), user).apply();
    }

    public String getName() {
        String defaultValue = context.getResources().getString(R.string.user_name);
        return sharedPref.getString(context.getString(R.string.user_name), defaultValue);
    }

    public void setEmail(String email) {
        editor.putString(context.getString(R.string.email), email).apply();
    }

    public String getEmail() {
        String defaultValue = context.getResources().getString(R.string.email);
        return sharedPref.getString(context.getString(R.string.email), defaultValue);
    }

    public void setProfileImageUrl(String url) {
        editor.putString(context.getString(R.string.photo_url), url).apply();
    }

    public String getProfileImageUrl() {
        String defaultValue = context.getResources().getString(R.string.photo_url);
        return sharedPref.getString(context.getString(R.string.photo_url), defaultValue);
    }

    public void putLoginWith(String loginFrom) {
        editor.putString(context.getString(R.string.loginFrom), loginFrom).apply();
    }

    public String getLoginWith() {
        String defaultValue = context.getResources().getString(R.string.loginFrom);
        return sharedPref.getString(context.getString(R.string.loginFrom), defaultValue);
    }

    public void clearPreferences() {
        editor.clear().apply();
    }

    public void setMPin(String mPin) {
        editor.putString(context.getString(R.string.mpin), mPin).apply();
    }

    public String getMPin() {
        String defaultValue = context.getResources().getString(R.string.mpin);
        return sharedPref.getString(context.getString(R.string.mpin), defaultValue);
    }

    public void setPassword(String password) {
        editor.putString(context.getString(R.string.password), password).apply();
    }

    public String getPassword() {
        String defaultValue = context.getResources().getString(R.string.password);
        return sharedPref.getString(context.getString(R.string.password), defaultValue);
    }

    public void setMobileNumber(String mobileNumber) {
        editor.putString(context.getString(R.string.mobile_number), mobileNumber).apply();
    }

    public String getMobileNumber() {
        String defaultValue = context.getResources().getString(R.string.mobile_number);
        return sharedPref.getString(context.getString(R.string.mobile_number), defaultValue);
    }

    public void setToken(String token) {
        editor.putString(context.getString(R.string.user_token), token).apply();
    }

    public String getToken() {
        String defaultValue = context.getResources().getString(R.string.user_token);
        return sharedPref.getString(context.getString(R.string.user_token), defaultValue);
    }

    public void setAppRating(Float rate) {
        editor.putString(context.getString(R.string.app_rate), rate.toString()).apply();
    }

    public String getAppRating() {
        String defaultValue = context.getResources().getString(R.string.app_rate);
        return sharedPref.getString(context.getString(R.string.app_rate), defaultValue);
    }

}
