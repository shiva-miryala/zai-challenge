package com.challenge.zai.service;

import com.challenge.zai.model.openweathermap.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${key.openweathermap}")
    private String key;

    @Value("${openweathermap.url}")
    private String openWeatherMapUrl;

    public OpenWeatherMapResponse getWeather(String city){
        URI uri = UriComponentsBuilder.fromHttpUrl(openWeatherMapUrl)
                // OpenWeatherMap is treating Melbourne as Melbourne of US.. so HardCoding to Melbourne,AU
                .queryParam("q","Melbourne,AU")
                .queryParam("appid",key)
                .queryParam("units","metric")
                .build().toUri();
        return restTemplate.getForEntity(uri, OpenWeatherMapResponse.class).getBody();
    }


}
