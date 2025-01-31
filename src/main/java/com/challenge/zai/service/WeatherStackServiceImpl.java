package com.challenge.zai.service;

import com.challenge.zai.model.weatherstack.WeatherStackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class WeatherStackServiceImpl implements WeatherStackService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Cacheable("weatherStack")
    public WeatherStackResponse getWeather(String city) {
        //No need to specify units param since default is Metric(Celsius and Km/Hr)
        URI uri = UriComponentsBuilder.fromHttpUrl("https://api.weatherstack.com/current")
                .queryParam("access_key", "0cc4e62a439dea44820263b803aa93a2")
                .queryParam("query", city)
                .build().toUri();
        return restTemplate.getForEntity(uri, WeatherStackResponse.class).getBody();
    }

}
