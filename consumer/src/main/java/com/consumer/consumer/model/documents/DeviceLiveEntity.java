package com.consumer.consumer.model.documents;

import com.consumer.consumer.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "device")
public class DeviceLiveEntity {
    @Id
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
