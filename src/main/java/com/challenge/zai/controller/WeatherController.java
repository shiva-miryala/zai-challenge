package com.challenge.zai.controller;

import com.challenge.zai.model.WeatherReport;
import com.challenge.zai.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherReport getWeather(@RequestParam(defaultValue = "melbourne") String city){
        return weatherService.getWeather(city);
    }
}
