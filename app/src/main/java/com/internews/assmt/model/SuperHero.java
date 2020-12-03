package com.internews.assmt.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

public class SuperHero extends SugarRecord<SuperHero> {

    @SerializedName("remote-id")
    @Expose
    private int remoteId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("powerstats")
    @Expose
    private PowerStat power;

    @SerializedName("biography")
    @Expose
    private Biography biography;

    @SerializedName("appearance")
    @Expose
    private Appearance appearance;

    @SerializedName("work")
    @Expose
    private Work work;

    @SerializedName("connections")
    @Expose
    private Connection connection;

    @SerializedName("image")
    @Expose
    private Image image;

    public SuperHero() {
    }

    public SuperHero(int remoteId, String name, PowerStat power, Biography biography, Appearance appearance, Work work, Connection connection, Image image) {
        this.remoteId = remoteId;
        this.name = name;
        this.power = power;
        this.biography = biography;
        this.appearance = appearance;
        this.work = work;
        this.connection = connection;
        this.image = image;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerStat getPower() {
        return power;
    }

    public void setPower(PowerStat power) {
        this.power = power;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static List<SuperHero> searchSuperHeroes(String query) {
        return SuperHero.find(SuperHero.class, "name like ?", query + "%");
    }

    public static List<SuperHero> getAll() {
        return SuperHero.getAll();
    }
}
