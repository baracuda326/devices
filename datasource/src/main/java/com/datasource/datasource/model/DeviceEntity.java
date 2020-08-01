package com.datasource.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class DeviceEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer surrogate_id;
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
