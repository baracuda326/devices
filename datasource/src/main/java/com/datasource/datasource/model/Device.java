package com.datasource.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Device implements Serializable {
    private int id;
    private float wind_speed;
    private float wave_height;
    private float water_temperature;
    private float air_temperature;
    private double pressure;
    private int isAlive;
    private Location location;
    private String date_time;
}
