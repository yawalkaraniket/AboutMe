package com.aboutme.avenjr.aboutme.data;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZomatoApiResponse {
//    @SerializedName("R")
//    @Expose
//    publicc RestaurantResourceId restaurantResourceId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("thumb")
    @Expose
    public String thumb;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("cuisines")
    @Expose
    public String cuisines;
    @SerializedName("featured_image")
    @Expose
    public String photosUrl;
    @SerializedName("average_cost_for_two")
    @Expose
    public Integer averageCostForTwo;

    public Integer getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

//    public RestaurantResourceId getRestaurantResourceId() {
//        return restaurantResourceId;
//    }

//    public void setRestaurantResourceId(RestaurantResourceId restaurantResourceId) {
//        this.restaurantResourceId = restaurantResourceId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }
}
