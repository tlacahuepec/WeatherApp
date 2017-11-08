package com.edwardsanti.weatherapp.dagger.module;

import android.content.Context;

import com.edwardsanti.weatherapp.dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by santi on 11/7/2017.
 */
@Module
public class AppContextModule {


    private final Context context;

    public AppContextModule(Context aContext) {
        this.context = aContext;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return context;
    }

}