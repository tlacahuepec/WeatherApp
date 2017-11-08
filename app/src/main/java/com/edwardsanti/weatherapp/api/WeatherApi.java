package com.edwardsanti.weatherapp.api;

import com.edwardsanti.weatherapp.BuildConfig;
import com.edwardsanti.weatherapp.model.Weather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by santi on 11/7/2017.
 */

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Observable<Weather> getWeather(@Query("q") String cityName);

}
