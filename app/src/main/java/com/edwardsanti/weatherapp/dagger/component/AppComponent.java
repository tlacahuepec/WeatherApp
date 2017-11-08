package com.edwardsanti.weatherapp.dagger.component;

import com.edwardsanti.weatherapp.dagger.module.AppContextModule;
import com.edwardsanti.weatherapp.dagger.module.NetworkModule;
import com.edwardsanti.weatherapp.dagger.module.WeatherApiServiceModule;
import com.edwardsanti.weatherapp.dagger.scope.AppScope;
import com.edwardsanti.weatherapp.ui.MainActivity;

import dagger.Component;

/**
 * Created by santi on 11/7/2017.
 */
@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, WeatherApiServiceModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
