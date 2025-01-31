package com.challenge.zai.model.openweathermap;

import lombok.Data;

import java.io.Serializable;

@Data
public class OpenWeatherMapResponse implements Serializable {

    private Main main;
    private Wind wind;
}
