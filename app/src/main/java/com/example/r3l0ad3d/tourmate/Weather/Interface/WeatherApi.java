package com.example.r3l0ad3d.tourmate.Weather.Interface;

import com.example.r3l0ad3d.tourmate.Weather.ModelClass.WeatherModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mdjahirulislam on 20/04/17.
 */

public interface WeatherApi {
    @GET()
    Call<WeatherModelResponse> getWeatherResponse(@Url String urlString);
}
