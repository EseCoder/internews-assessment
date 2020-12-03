package com.internews.assmt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Work extends SugarRecord<Work> {

    @SerializedName("occupation")
    @Expose
    private String occupation;

    @SerializedName("base")
    @Expose
    private String base;

    public Work() {
    }

    public Work(String occupation, String base) {
        this.occupation = occupation;
        this.base = base;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
