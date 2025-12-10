package com.example.car_rental_21f21500;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    // Below is the location of Muscat (for weather display)
    @GET("forecast?latitude=23.5859&longitude=58.4059&current_weather=true")
    Call<WeatherResponse> getWeather();
}

