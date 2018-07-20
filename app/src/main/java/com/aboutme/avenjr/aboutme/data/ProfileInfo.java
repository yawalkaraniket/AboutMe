package com.aboutme.avenjr.aboutme.data;

import java.util.ArrayList;

public class ProfileInfo {

    private static ArrayList<String> allUserProfileSections= new ArrayList<>();
    private ArrayList<String> hobbies = new ArrayList<>();
    private static ArrayList<String> otherActivities = new ArrayList<>();
    private static ArrayList<String> educational = new ArrayList<>();
    private static ArrayList<String> entertainment = new ArrayList<>();

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
        this.hobbies.add("Cricket");
        this.hobbies.add("Poetry");
        this.hobbies.add("Coins Collection");
        this.hobbies.add("Dramas");
        this.hobbies.add("Books Reading");
        this.hobbies.add("HolyBoll");
    }

    public ArrayList<String> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(ArrayList<String> otherActivities) {
        ProfileInfo.otherActivities = otherActivities;
    }

    public ArrayList<String> getEducational() {
        return educational;
    }

    public void setEducational(ArrayList<String> educational) {
        ProfileInfo.educational = educational;
    }

    public ArrayList<String> getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(ArrayList<String> entertainment) {
        ProfileInfo.entertainment = entertainment;
    }
}