package com.challenge.zai.service;

import com.challenge.zai.model.WeatherReport;
import com.challenge.zai.model.openweathermap.OpenWeatherMapResponse;
import com.challenge.zai.model.weatherstack.WeatherStackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Backoff;


@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private WeatherStackService weatherStackService;

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public WeatherReport getWeather(String city) {

        WeatherStackResponse weatherStackResponse = weatherStackService.getWeather(city);
        WeatherReport response = new WeatherReport();
        response.setWindSpeed(weatherStackResponse.getCurrent().getWind_speed());
        response.setTemperature(weatherStackResponse.getCurrent().getTemperature());
        return response;
    }

    @Recover
    public WeatherReport getWeatherFrom(String city){
        OpenWeatherMapResponse openWeatherMapResponse = openWeatherMapService.getWeather(city);
        WeatherReport report = new WeatherReport();
        report.setTemperature(openWeatherMapResponse.getMain().getTemp());
        //Convert Windspeed from meter/sec to Km/Hour
        float speed = openWeatherMapResponse.getWind().getSpeed()*3.6f;
        report.setWindSpeed(speed);
        return report;
    }
}
