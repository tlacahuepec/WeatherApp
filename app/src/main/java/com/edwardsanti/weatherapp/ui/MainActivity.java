package com.edwardsanti.weatherapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edwardsanti.weatherapp.BuildConfig;
import com.edwardsanti.weatherapp.R;
import com.edwardsanti.weatherapp.api.WeatherService;
import com.edwardsanti.weatherapp.dagger.ApplicationController;
import com.edwardsanti.weatherapp.dagger.component.AppComponent;
import com.edwardsanti.weatherapp.model.Main;
import com.edwardsanti.weatherapp.model.Weather;
import com.edwardsanti.weatherapp.model.Weather_;
import com.edwardsanti.weatherapp.ui.presenter.MainPresenter;
import com.edwardsanti.weatherapp.ui.view.MainView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    public WeatherService service;

    private ProgressBar progressBar;
    private Button button;
    private EditText editText;

    private GridLayout infoLayout;
    private TextView tvNameTxt;
    private TextView tvMainTxt;
    private TextView tvDescriptionTxt;
    private ImageView ivIcon;
    private TextView tvHumidityTxt;
    private TextView tvPressureTxt;
    private TextView tvTempTxt;
    private TextView tvTempMaxTxt;
    private TextView tvTempMinTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        renderView();

        final MainPresenter presenter = new MainPresenter(service, this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getWeather(editText.getText().toString());
            }
        });
    }

    private void init() {
        ApplicationController.getAppComponent().inject(this);
    }

    private void renderView() {
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        button = findViewById(R.id.btnSearch);
        editText = findViewById(R.id.etCityName);

        infoLayout = findViewById(R.id.glInfo);
        tvNameTxt = findViewById(R.id.tvNameTxt);
        tvMainTxt = findViewById(R.id.tvMainTxt);
        tvDescriptionTxt = findViewById(R.id.tvDescriptionTxt);
        ivIcon = findViewById(R.id.ivIcon);
        tvHumidityTxt = findViewById(R.id.tvHumidityTxt);
        tvPressureTxt = findViewById(R.id.tvPressureTxt);
        tvTempTxt = findViewById(R.id.tvTempTxt);
        tvTempMaxTxt = findViewById(R.id.tvTempMaxTxt);
        tvTempMinTxt = findViewById(R.id.tvTempMinTxt);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        // Handle Error
    }

    @Override
    public void getWeatherSuccess(Weather weatherResponse) {
        infoLayout.setVisibility(View.VISIBLE);
        tvNameTxt.setText(weatherResponse.getName());
        List<Weather_> listWeather = weatherResponse.getWeather();
        Weather_ weather = listWeather.get(0);
        tvMainTxt.setText(weather.getMain());
        tvDescriptionTxt.setText(weather.getDescription());

        Picasso
                .with(this)
                .load(BuildConfig.API_URL_IMG + weather.getIcon() + ".png")
                .placeholder(R.drawable.ic_android_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(ivIcon);

        Main mainWeather = weatherResponse.getMain();
        tvHumidityTxt.setText(mainWeather.getHumidity().toString());
        tvPressureTxt.setText(mainWeather.getPressure().toString());
        tvTempTxt.setText(mainWeather.getTemp().toString());
        tvTempMaxTxt.setText(mainWeather.getTempMax().toString());
        tvTempMinTxt.setText(mainWeather.getTempMin().toString());
    }

}
