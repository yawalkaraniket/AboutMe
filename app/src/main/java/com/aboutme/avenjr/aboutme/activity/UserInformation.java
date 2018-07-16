package com.aboutme.avenjr.aboutme.activity;

/**
 * Created by tudip on 11/4/18.
 */

public class UserInformation {

    public static String name;
    public static String email;
    public static String password;
    public static String mobileNo;
    public static String country;
    public static String countryCode;

    public UserInformation() {

    }


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
        this.mobileNo = mobileNo;
    }



    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    UserInformation(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
