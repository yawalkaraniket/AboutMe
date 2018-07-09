package com.aboutme.avenjr.aboutme.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Rule;

import java.util.HashMap;
import java.util.Random;

public class TestUtil {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static HashMap<String,String > getLoginCredentials(){
        HashMap<String, String> emailPassword = new HashMap<>();
        return emailPassword;
    }

    public static String generateRandomString(String string, int length) {
        char[] chars = string.toCharArray();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int stringLength = random.nextInt(string.length());
        for(int index = 0;index<stringLength;index++){
         char c = chars[stringLength];
         stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
