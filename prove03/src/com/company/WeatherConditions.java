package com.company;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WeatherConditions {
    public int id;
    public String name;
    @SerializedName("main")
    public Map<String, Float> measurements;

    public WeatherConditions(int id, String name, Map<String,Float> main){
        this.id = id;
        this.name= name;
        this.measurements = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Float> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Map<String, Float> measurements) {
        this.measurements = measurements;
    }
}