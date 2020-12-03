package com.internews.assmt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class PowerStat extends SugarRecord<PowerStat> {

    @SerializedName("intelligence")
    @Expose
    private int intelligence;

    @SerializedName("strength")
    @Expose
    private int strength;

    @SerializedName("speed")
    @Expose
    private int speed;

    @SerializedName("durability")
    @Expose
    private int durability;

    @SerializedName("power")
    @Expose
    private int power;

    @SerializedName("combat")
    @Expose
    private int combat;

    public PowerStat() {
    }

    public PowerStat(int intelligence, int strength, int speed, int durability, int power, int combat) {
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }
}
