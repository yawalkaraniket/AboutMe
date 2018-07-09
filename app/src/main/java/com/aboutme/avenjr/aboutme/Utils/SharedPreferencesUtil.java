package com.aboutme.avenjr.aboutme.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aboutme.avenjr.aboutme.R;

public class SharedPreferencesUtil {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Context context;

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
        String defaultValue = context.getResources().getString(R.string.user_name).toString();
        return sharedPref.getString(context.getString(R.string.user_name), defaultValue);
    }

    public void setEmail(String email) {
        editor.putString(context.getString(R.string.email), email).apply();
    }

    public String getEmail() {
        String defaultValue = context.getResources().getString(R.string.email).toString();
        return sharedPref.getString(context.getString(R.string.email), defaultValue);
    }

    public void setProfileImageUrl(String url){
        editor.putString(context.getString(R.string.photo_url),url).apply();
    }

    public String getProfileImageUrl(){
        String defaultValue = context.getResources().getString(R.string.photo_url).toString();
        return sharedPref.getString(context.getString(R.string.photo_url), defaultValue);
    }

    public void putLoginWith(String loginFrom){
        editor.putString(context.getString(R.string.loginFrom),loginFrom).apply();
    }

    public String getLoginWith(){
        String defaultValue = context.getResources().getString(R.string.loginFrom).toString();
        return sharedPref.getString(context.getString(R.string.loginFrom), defaultValue);
    }

    public void clearPreferences(){
        editor.clear().apply();
    }

}
