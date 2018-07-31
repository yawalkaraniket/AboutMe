package com.aboutme.avenjr.aboutme.data;

import java.util.ArrayList;

public class ProfileInfo {

    private ArrayList<String> hobbies = new ArrayList<>();
    private ArrayList<String> otherActivities = new ArrayList<>();
    private ArrayList<String> educationalCategories = new ArrayList<>();
    private ArrayList<String> entertainment = new ArrayList<>();

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies() {
        this.hobbies.add("Poetry");
        this.hobbies.add("Coins Collection");
        this.hobbies.add("Books Reading");
    }

    public ArrayList<String> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities() {
        this.otherActivities.add("Cricket");
        this.otherActivities.add("Dramas");
        this.otherActivities.add("HolyBoll");
    }

    public ArrayList<String> getEducationalCategories() {
        return educationalCategories;
    }

    public void setEducationalCategories() {
        this.educationalCategories.add("Pre-Primary Education");
        this.educationalCategories.add("Primary Education");
        this.educationalCategories.add("Secondary Education");
        this.educationalCategories.add("Higher Secondary Education");
        this.educationalCategories.add("Diploma Information");
        this.educationalCategories.add("Graduation Information");
        this.educationalCategories.add("Post Graduation Information");
        this.educationalCategories.add("Projects Information");
    }

    public ArrayList<String> getEntertainmentSection() {
        return entertainment;
    }

    public void setEntertainmentSection() {
        this.entertainment.add("Movies");
        this.entertainment.add("Songs");
    }
}
