package com.mmnabi.weatherproject.common.services;

import com.mmnabi.weatherproject.models.CurrentWeatherResponse;
import com.mmnabi.weatherproject.models.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Hard-won on 1/16/2018.
 */

public interface WeatherService {
    @GET()
    Call<CurrentWeatherResponse> getCurrentWeatherData(@Url String url);

    @GET()
    Call<ForecastResponse> getForecastWeatherData(@Url String url);
}
