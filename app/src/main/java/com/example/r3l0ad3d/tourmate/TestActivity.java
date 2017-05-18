package com.example.r3l0ad3d.tourmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.r3l0ad3d.tourmate.Temporary.WeatherReport;
import com.example.r3l0ad3d.tourmate.Weather.Adapter.AdapterWeatherForeCast;
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

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AdapterWeatherForeCast adapter;
    private List<ForeCastReport> foreCastReportList;
    private String cityName = "Dhaka";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        foreCastReportList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rvWeatherForeCast);

        adapter = new AdapterWeatherForeCast(this,foreCastReportList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        getReport();

        /*WeatherReport report = new WeatherReport();
        report.getReport();
        if(report.getForeCastReportList().size()>0){
            foreCastReportList.addAll(report.getForeCastReportList());
            adapter.notifyDataSetChanged();
        }*/

    }

    private void getReport() {

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
                    adapter.notifyDataSetChanged();
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


}
