package com.example.huynh.weatherappfinal.Json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huynh on 08-Aug-16.
 */
public class JsonModel {
    public static final String MAIN = "main";
    public static final String WIND="wind";
    private  static  final String WEATHER ="weather";

    @SerializedName(WEATHER)
    public List<JsonWeather> jsonWeathers;

    @SerializedName(MAIN)
    public JsonMain jsonMain;
    @SerializedName(WIND)
    public  JsonWind jsonWind;

    public JsonMain getJsonMain() {
        return jsonMain;
    }

    public void setJsonMain(JsonMain jsonMain) {
        this.jsonMain = jsonMain;
    }

    public JsonWind getJsonWind() {
        return jsonWind;
    }

    public void setJsonWind(JsonWind jsonWind) {
        this.jsonWind = jsonWind;
    }

    public List<JsonWeather> getJsonWeathers() {
        return jsonWeathers;
    }

    public void setJsonWeathers(List<JsonWeather> jsonWeathers) {
        this.jsonWeathers = jsonWeathers;
    }
}
