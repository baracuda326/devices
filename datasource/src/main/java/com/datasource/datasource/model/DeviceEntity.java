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
    private Integer surrogateId;
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
