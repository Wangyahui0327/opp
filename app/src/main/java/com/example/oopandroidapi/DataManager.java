package com.example.oopandroidapi;

import android.util.Log;

import java.util.ArrayList;

public class DataManager {

    private static volatile DataManager dataManager;
    private String weatherDataAsString;
    private String workPlaceSelfSufficiencyDataAsString;
    private String populationDataAsString;
    private String city;
    private int score;
    private ArrayList<PopulationData> populationDataArrayList;
    private String employRates;

    public static DataManager getInstance() {
        if (dataManager == null) {
            synchronized (DataManager.class) {
                if (dataManager == null) {
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    private DataManager() {
    }

    public void setCityData(String city) {

        this.city = city;
    }
    public void setWeatherData(String weatherDataAsString) {

        this.weatherDataAsString = weatherDataAsString;
    }

    public void setWorkPlaceSelfSufficiencyData(String workPlaceSelfSufficiencyDataAsString) {

        this.workPlaceSelfSufficiencyDataAsString = workPlaceSelfSufficiencyDataAsString;
    }

    public void setPopulationData(String populationDataAsString) {

        this.populationDataAsString = populationDataAsString;
    }

    public String getEmployRates() {
        return employRates;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        Log.e("选择结果", "当前分数 : " + score);
    }

    public String getWeatherDataAsString() {
        return weatherDataAsString;
    }

    public String getWorkPlaceSelfSufficiencyDataAsString() {
        return workPlaceSelfSufficiencyDataAsString;
    }

    public String getPopulationDataAsString() {
        return populationDataAsString;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<PopulationData> getPopulationDataArrayList() {
        return populationDataArrayList;
    }

    public void setPopulationDataList(ArrayList<PopulationData> PopulationDataArrayList) {

        populationDataArrayList = PopulationDataArrayList;
    }


    private ChoiceListener choiceListener;
    public void setChoiceListener(ChoiceListener choiceListener) {
        this.choiceListener = choiceListener;
    }

    public ChoiceListener getChoiceListener() {
        return choiceListener;
    }

    public void setEmployRates(String employRates) {

        this.employRates = employRates;
    }

    public interface ChoiceListener {
        void onChoice(boolean isCorrect, int nextPosition);
    }
}
