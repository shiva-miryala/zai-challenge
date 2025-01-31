package com.challenge.zai.model.openweathermap;

import lombok.Data;

import java.io.Serializable;

@Data
public class Wind implements Serializable {
    private float speed;
}
