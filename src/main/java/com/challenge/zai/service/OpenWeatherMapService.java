package com.challenge.zai.service;

import com.challenge.zai.model.openweathermap.OpenWeatherMapResponse;

public interface OpenWeatherMapService {

    OpenWeatherMapResponse getWeather(String city);
}
