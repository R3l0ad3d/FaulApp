package com.example.r3l0ad3d.tourmate.Weather.ModelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r3l0ad3d on 5/1/17.
 */

public class ForeCastReport {
    private String date;
    private String report;
    private String maxTemperature;
    private String minTemperature;
    private String imageURI;

    protected static String image = "http://fullnightfun.com/weather_icon/";

    private List<ForeCastReport> foreCastReportList = new ArrayList<>();

    public ForeCastReport(WeatherModelResponse weatherModelResponse) {
        this.Convert(weatherModelResponse);
    }

    public ForeCastReport(String date, String report, String maxTemperature, String minTemperature, String imageUri) {
        this.date = date;
        this.report = report;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.imageURI = imageUri;
    }

    private void Convert(WeatherModelResponse weatherModelResponse) {
        int size  = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().size();
        for(int i=0;i<size;i++){
            String code,date,high,low,text,img;
            code = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(i).getCode();
            date = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(i).getDate();
            high = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(i).getHigh();
            low = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(i).getLow();
            text = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(i).getText();
            img = image+code+".gif";
            ForeCastReport report = new ForeCastReport(date,text,high,low,img);

            foreCastReportList.add(report);
        }
    }

    public String getDate() {
        return date;
    }

    public String getReport() {
        return report;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getImageURI() {
        return imageURI;
    }

    public List<ForeCastReport> getForeCastReportList() {
        return foreCastReportList;
    }
}
