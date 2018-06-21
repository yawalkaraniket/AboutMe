package com.aboutme.avenjr.aboutme.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Rule;

import java.util.HashMap;

public class TestUtil {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static HashMap<String,String > getLoginCredentials(){
        HashMap<String, String> emailPassword = new HashMap<>();
        return emailPassword;
    }
}
