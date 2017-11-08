package com.edwardsanti.weatherapp.dagger.module;

import com.edwardsanti.weatherapp.BuildConfig;
import com.edwardsanti.weatherapp.api.WeatherApi;
import com.edwardsanti.weatherapp.api.WeatherService;
import com.edwardsanti.weatherapp.dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by santi on 11/7/2017.
 */
@Module
public class WeatherApiServiceModule {

    @AppScope
    @Provides
    WeatherApi provideApiService(OkHttpClient client, GsonConverterFactory gson) {
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(BuildConfig.API_URL).addConverterFactory(gson).
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        return retrofit.create(WeatherApi.class);
    }

    @AppScope
    @Provides
    WeatherService provideWeatherService(WeatherApi weatherApi) {
        return new WeatherService(weatherApi);
    }
}