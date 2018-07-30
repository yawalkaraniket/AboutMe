package com.aboutme.avenjr.aboutme.data;

import java.util.ArrayList;

public class ProfileInfo {

    private static ArrayList<String> allUserProfileSections= new ArrayList<>();
    private ArrayList<String> hobbies = new ArrayList<>();
    private ArrayList<String> otherActivities = new ArrayList<>();
    private ArrayList<String> educationalCategories = new ArrayList<>();
    private ArrayList<String> entertainment = new ArrayList<>();

    public ArrayList<String> getAllUserProfileSections() {
        return allUserProfileSections;
    }

    public void setAllUserProfileSections(String allUserProfileSections) {
        if(!ProfileInfo.allUserProfileSections.contains(allUserProfileSections)){
            ProfileInfo.allUserProfileSections.add(allUserProfileSections);
        }
    }

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
        this.educationalCategories.add("Pre-Primary");
        this.educationalCategories.add("Primary");
        this.educationalCategories.add("Secondary");
        this.educationalCategories.add("Higher Secondary");
        this.educationalCategories.add("Diploma");
        this.educationalCategories.add("Graduation");
        this.educationalCategories.add("Post Graduation");
        this.educationalCategories.add("Projects");
    }

    public ArrayList<String> getEntertainmentSection() {
        return entertainment;
    }

    public void setEntertainmentSection() {
        this.entertainment.add("Movies");
        this.entertainment.add("Songs");
    }
}
