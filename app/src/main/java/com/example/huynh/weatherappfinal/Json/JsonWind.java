package com.example.huynh.weatherappfinal.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 08-Aug-16.
 */
public class JsonWind {
    public static final String SPEED ="speed";

    @SerializedName(SPEED)
    public double speed;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
