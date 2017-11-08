package com.edwardsanti.weatherapp.ui.presenter;

import com.edwardsanti.weatherapp.api.WeatherService;
import com.edwardsanti.weatherapp.model.Weather;
import com.edwardsanti.weatherapp.ui.view.MainView;
import com.edwardsanti.weatherapp.uitl.NetworkError;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by santi on 11/7/2017.
 */

public class MainPresenter {

    private final WeatherService service;
    private final MainView view;
    private CompositeSubscription subscriptions;

    public MainPresenter(WeatherService service, MainView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getWeather(String cityName) {
        view.showWait();

        Subscription subscription = service.getWeather(new WeatherService.GetWeatherCallback() {
            @Override
            public void onSuccess(Weather WeatherResponse) {
                view.removeWait();
                view.getWeatherSuccess(WeatherResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        }, cityName);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}