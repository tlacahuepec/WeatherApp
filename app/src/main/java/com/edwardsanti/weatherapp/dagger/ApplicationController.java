package com.edwardsanti.weatherapp.dagger;

import android.app.Application;

import com.edwardsanti.weatherapp.dagger.component.AppComponent;
import com.edwardsanti.weatherapp.dagger.component.DaggerAppComponent;
import com.edwardsanti.weatherapp.dagger.module.AppContextModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by santi on 11/8/2017.
 */
public class ApplicationController extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }


    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  handle
                }
            });
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}