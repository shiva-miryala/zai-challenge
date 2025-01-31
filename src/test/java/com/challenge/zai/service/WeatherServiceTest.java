package com.challenge.zai.service;

import com.challenge.zai.model.WeatherReport;
import com.challenge.zai.model.openweathermap.Main;
import com.challenge.zai.model.openweathermap.OpenWeatherMapResponse;
import com.challenge.zai.model.openweathermap.Wind;
import com.challenge.zai.model.weatherstack.Current;
import com.challenge.zai.model.weatherstack.WeatherStackResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherStackService weatherStackService;
    @Mock
    private OpenWeatherMapService openWeatherMapService;

    @InjectMocks
    private WeatherServiceImpl weatherService;
    
    @Test
    public void testGetWeather(){
        WeatherStackResponse mockResponse = getWeatherStackResponse();
        Mockito.when(weatherStackService.getWeather(Mockito.anyString())).thenReturn(mockResponse);
        WeatherReport weatherReport = weatherService.getWeather("Melbourne");
        Assertions.assertEquals(12.3f, weatherReport.getTemperature());
        Assertions.assertEquals(23.4f, weatherReport.getWindSpeed());
        Mockito.verify(weatherStackService).getWeather(Mockito.anyString());
        Mockito.verify(openWeatherMapService, Mockito.never()).getWeather(Mockito.anyString());
    }

    @Test
    public void testGetWeatherFrom(){
        OpenWeatherMapResponse mockResponse = getOpenWeatherMapResponse();
        Mockito.when(openWeatherMapService.getWeather(Mockito.anyString())).thenReturn(mockResponse);
        WeatherReport weatherReport = weatherService.getWeatherFrom("Melbourne");
        Assertions.assertEquals(34.5f, weatherReport.getTemperature());
        Assertions.assertEquals(8.9f*3.6f, weatherReport.getWindSpeed());
        Mockito.verify(weatherStackService, Mockito.never()).getWeather(Mockito.anyString());
        Mockito.verify(openWeatherMapService).getWeather(Mockito.anyString());
    }

    private static OpenWeatherMapResponse getOpenWeatherMapResponse() {
        OpenWeatherMapResponse mockResponse = new OpenWeatherMapResponse();
        Main main = new Main();
        main.setTemp(34.5f);
        mockResponse.setMain(main);
        Wind wind = new Wind();
        wind.setSpeed(8.9f);
        mockResponse.setWind(wind);
        return mockResponse;
    }

    private static WeatherStackResponse getWeatherStackResponse() {
        WeatherStackResponse mockResponse = new WeatherStackResponse();
        Current current = new Current();
        current.setTemperature(12.3f);
        current.setWind_speed(23.4f);
        mockResponse.setCurrent(current);
        return mockResponse;
    }
}
