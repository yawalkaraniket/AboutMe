package com.aboutme.avenjr.aboutme.data;

import java.util.ArrayList;

public class ProfileListInfo {
    private String name;
    private ArrayList<String> hobbies = new ArrayList<>();
    private String email;

    public void ProfileListInfo(String name, ArrayList<String> hobbies, String email){
        this.name = name;
        this.email=email;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public String getEmail() {
        return email;
    }
}
