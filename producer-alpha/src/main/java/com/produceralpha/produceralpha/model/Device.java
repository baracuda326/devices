package com.produceralpha.produceralpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device implements Serializable {
    private int id;
    private float windSpeed;
    private float waveHeight;
    private float waterTemperature;
    private float airTemperature;
    private double pressure;
    private int isAlive;
    private Location location;
    private String dateTime;
}

