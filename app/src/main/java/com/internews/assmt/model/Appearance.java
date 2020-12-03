package com.internews.assmt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Appearance extends SugarRecord<Appearance> {

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("race")
    @Expose
    private String race;

    @SerializedName("height")
    @Expose
    private String[] height;

    @SerializedName("weight")
    @Expose
    private String[] weight;

    @SerializedName("eye-color")
    @Expose
    private String eyeColor;

    @SerializedName("hair-color")
    @Expose
    private String hairColor;

    public Appearance() {
    }

    public Appearance(String gender, String race, String[] height, String[] weight, String eyeColor, String hairColor) {
        this.gender = gender;
        this.race = race;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String[] getHeight() {
        return height;
    }

    public void setHeight(String[] height) {
        this.height = height;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
}
