package com.internews.assmt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Biography extends SugarRecord<Biography> {

    @SerializedName("full-name")
    @Expose
    private String fullName;

    @SerializedName("alter-egos")
    @Expose
    private String alterEgo;

    @SerializedName("aliases")
    @Expose
    private String[] aliases;

    @SerializedName("place-of-birth")
    @Expose
    private String placeOfBirth;

    @SerializedName("first-appearance")
    @Expose
    private String firstAppearance;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("alignment")
    @Expose
    private String alignment;

    public Biography() {
    }

    public Biography(String fullName, String alterEgo, String[] aliases, String placeOfBirth, String firstAppearance, String publisher,
                     String alignment) {
        this.fullName = fullName;
        this.alterEgo = alterEgo;
        this.aliases = aliases;
        this.placeOfBirth = placeOfBirth;
        this.firstAppearance = firstAppearance;
        this.publisher = publisher;
        this.alignment = alignment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
