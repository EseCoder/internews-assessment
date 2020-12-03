package com.internews.assmt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Connection extends SugarRecord<Connection> {

    @SerializedName("group-affiliation")
    @Expose
    private String groupAffiliation;

    @SerializedName("relatives")
    @Expose
    private String relatives;

    public Connection() {
    }

    public Connection(String groupAffiliation, String relatives) {
        this.groupAffiliation = groupAffiliation;
        this.relatives = relatives;
    }

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation) {
        this.groupAffiliation = groupAffiliation;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }
}
