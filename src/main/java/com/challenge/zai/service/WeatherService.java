package com.challenge.zai.service;

import com.challenge.zai.model.WeatherReport;

public interface WeatherService {
    public WeatherReport getWeather(String city);
}
