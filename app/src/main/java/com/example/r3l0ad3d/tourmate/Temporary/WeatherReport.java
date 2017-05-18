package com.example.r3l0ad3d.tourmate.Temporary;

import android.util.Log;

import com.example.r3l0ad3d.tourmate.Weather.Interface.WeatherApi;
import com.example.r3l0ad3d.tourmate.Weather.ModelClass.ForeCastReport;
import com.example.r3l0ad3d.tourmate.Weather.ModelClass.WeatherModelResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r3l0ad3d on 5/15/17.
 */

public class WeatherReport {
    private String cityName="dhaka";
    private List<ForeCastReport> foreCastReportList = new ArrayList<>();
    public void getReport() {

        final String BASE_URL = "https://query.yahooapis.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        String url = "v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" +
                cityName+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        Call<WeatherModelResponse> weatherModelResponseCall = weatherApi.getWeatherResponse(url);

        weatherModelResponseCall.enqueue(new Callback<WeatherModelResponse>() {
            @Override
            public void onResponse(Call<WeatherModelResponse> call, Response<WeatherModelResponse> response) {
                if(response.code()==200){
                    WeatherModelResponse responsList = response.body();
                    ForeCastReport reportList = new ForeCastReport(responsList);
                    foreCastReportList.addAll(reportList.getForeCastReportList());
                }else {
                    Log.d("Error","Connection Problem");
                }
            }

            @Override
            public void onFailure(Call<WeatherModelResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public List<ForeCastReport> getForeCastReportList() {
        return foreCastReportList;
    }
}
