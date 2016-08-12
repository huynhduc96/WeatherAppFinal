package com.example.huynh.weatherappfinal.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 08-Aug-16.
 */
public class JsonMain {
    public static final String TEMP ="temp";
    public static final String HUMIDITY ="humidity";

    @SerializedName(TEMP)
    public double temp;
    @SerializedName(HUMIDITY)
    public  double humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
