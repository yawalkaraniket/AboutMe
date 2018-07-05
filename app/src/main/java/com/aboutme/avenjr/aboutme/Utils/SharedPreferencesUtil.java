package com.aboutme.avenjr.aboutme.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aboutme.avenjr.aboutme.R;

public class SharedPreferencesUtil {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPreferencesUtil(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferenxce_file_key), Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
    }

    public void setUser() {
        editor.putString(context.getString(R.string.shared), "aniket");
        editor.apply();
    }

    public String getUser() {
        String defaultValue = context.getResources().getString(R.string.shared).toString();
        return sharedPref.getString(context.getString(R.string.shared), defaultValue);
    }
}
