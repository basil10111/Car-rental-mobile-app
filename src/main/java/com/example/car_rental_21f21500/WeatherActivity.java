package com.example.car_rental_21f21500;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    Button btnFetchWeather, eeaa;
    TextView txtWeatherResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        btnFetchWeather = findViewById(R.id.btnFetchWeather);
        txtWeatherResult = findViewById(R.id.txtWeatherResult);
        eeaa=findViewById(R.id.ea);

        btnFetchWeather.setOnClickListener(v -> fetchWeather());
        eeaa.setOnClickListener(view -> finish());
    }


    private void fetchWeather() {
        WeatherApi api = RetrofitClient.getInstance();
        api.getWeather().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful() && response.body() != null && response.body().current_weather != null) {
                    CurrentWeather cw = response.body().current_weather;
                    String weatherText = "Temperature in Muscat: " + cw.temperature + "°C\n" +
                            "Wind Speed: " + cw.windspeed + " km/h";
                    txtWeatherResult.setText(weatherText);
                } else {
                    txtWeatherResult.setText("Failed to load weather.");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                txtWeatherResult.setText("Error: " + t.getMessage());
            }
        });
    }

}

