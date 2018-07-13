package com.aboutme.avenjr.aboutme.activity;

/**
 * Created by tudip on 11/4/18.
 */

public class UserInformation {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String mobileNo;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    UserInformation(String email, String password, String name,String mobileNo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNo = mobileNo;
    }
}
