package com.example.huynh.weatherappfinal.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 12-Aug-16.
 */
public class JsonWeather  {
    private static final String MAIN = "main";
    private static final String DESCRIP = "description";

    @SerializedName(MAIN)
    private String Main;

    @SerializedName(DESCRIP)
    private String description;

    public String getMain() {
        return Main;
    }

    public String getDescription() {
        return description;
    }

    public void setMain(String main) {
        Main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
