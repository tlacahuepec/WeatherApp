package com.edwardsanti.weatherapp.ui.view;

import com.edwardsanti.weatherapp.model.Weather;

/**
 * Created by santi on 11/8/2017.
 */

public interface MainView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getWeatherSuccess(Weather WeatherResponse);

}
