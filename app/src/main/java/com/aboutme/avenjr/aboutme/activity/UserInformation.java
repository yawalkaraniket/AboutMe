package com.aboutme.avenjr.aboutme.activity;

import java.util.ArrayList;

/**
 * Created by tudip on 11/4/18.
 */

public class UserInformation {

    public static String name;
    public static String email;
    public static String password;
    private static String mobileNo;
    private static String country;
    private static String countryCode;
    private static String mPin;

    public String getDatabaseKey() {
        return databaseKey;
    }

    public void setDatabaseKey(String databaseKey) {
        UserInformation.databaseKey = databaseKey;
    }

    private static String databaseKey;

    public void setMPin(String mPin) {
        UserInformation.mPin = mPin;
    }

    public String getMPin() {
        return mPin;
    }

    public UserInformation() { }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        UserInformation.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        UserInformation.countryCode = countryCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        UserInformation.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        UserInformation.email = email;
    }

    public void setPassword(String password) {
        UserInformation.password = password;
    }

    public void setName(String name) {
        UserInformation.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    UserInformation(String email, String password, String name) {
        UserInformation.email = email;
        UserInformation.password = password;
        UserInformation.name = name;
    }
}
