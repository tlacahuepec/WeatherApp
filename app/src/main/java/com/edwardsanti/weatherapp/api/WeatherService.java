package com.edwardsanti.weatherapp.api;

import com.edwardsanti.weatherapp.model.Weather;
import com.edwardsanti.weatherapp.uitl.NetworkError;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by santi on 11/8/2017.
 */

public class WeatherService {

    private final WeatherApi weatherApi;

    public WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Subscription getWeather(final GetWeatherCallback callback, String cityName) {

        return weatherApi.getWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(Weather weatherResponse) {
                        callback.onSuccess(weatherResponse);

                    }
                });
    }

    public interface GetWeatherCallback {
        void onSuccess(Weather weatherResponse);

        void onError(NetworkError networkError);
    }

}
