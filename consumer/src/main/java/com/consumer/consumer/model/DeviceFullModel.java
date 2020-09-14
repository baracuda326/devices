package com.consumer.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeviceFullModel {
    private List<Device> alarmList;
    private List<Device> brokenList;
    private List<Device> liveList;
}
