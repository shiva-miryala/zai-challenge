package com.challenge.zai.service;

import com.challenge.zai.model.weatherstack.WeatherStackResponse;

public interface WeatherStackService {

    WeatherStackResponse getWeather(String city);
}
