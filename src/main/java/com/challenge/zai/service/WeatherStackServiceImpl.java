package com.challenge.zai.service;

import com.challenge.zai.model.weatherstack.WeatherStackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class WeatherStackServiceImpl implements WeatherStackService{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weatherstack.url}")
    private String weatherstackUrl;

    @Value("${key.weatherstack}")
    private String accessKey;

    @Override
    @Cacheable("weatherStack")
    public WeatherStackResponse getWeather(String city) {
        //No need to specify units param since default is Metric(Celsius and Km/Hr)
        URI uri = UriComponentsBuilder.fromHttpUrl(weatherstackUrl)
                .queryParam("access_key", accessKey)
                .queryParam("query", city)
                .build().toUri();
        return restTemplate.getForEntity(uri, WeatherStackResponse.class).getBody();
    }

}
