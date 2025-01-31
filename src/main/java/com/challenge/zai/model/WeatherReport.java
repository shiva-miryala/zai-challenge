package com.challenge.zai.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherReport {
    @JsonProperty("wind_speed")
    private float windSpeed;
    @JsonProperty("temperature_degrees")
    private float temperature;
}
