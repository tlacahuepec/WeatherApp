package com.edwardsanti.weatherapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.edwardsanti.weatherapp.dagger.component.AppComponent;
import com.edwardsanti.weatherapp.dagger.component.DaggerAppComponent;

/**
 * Created by santi on 11/8/2017.
 */

public class BaseActivity extends AppCompatActivity {

   /* AppComponent appComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = DaggerAppComponent.builder().appContextModule().build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }*/
}
