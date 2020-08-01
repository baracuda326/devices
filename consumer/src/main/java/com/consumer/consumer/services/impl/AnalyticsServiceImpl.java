package com.consumer.consumer.services.impl;

import com.consumer.consumer.model.Device;
import com.consumer.consumer.model.DeviceFullModel;
import com.consumer.consumer.model.documents.DeviceAlarmEntity;
import com.consumer.consumer.model.documents.DeviceBrokenEntity;
import com.consumer.consumer.model.documents.DeviceLiveEntity;
import com.consumer.consumer.repository.DeviceAlarmRepository;
import com.consumer.consumer.repository.DeviceBrokenRepository;
import com.consumer.consumer.repository.DeviceLiveRepository;
import com.consumer.consumer.services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private static final float WIND_SPEED_MAX = 90;
    private static final float WAVE_HEIGHT_MAX = 6;
    private static final float WATER_TEMPERATURE_MAX = 40;
    private static final float AIR_TEMPERATURE_MIN = -30;
    private static final float AIR_TEMPERATURE_MAX = 50;
    private static final double PRESSURE_MIN = 641.3;
    private static final double PRESSURE_MAX = 815.85;
    private static final int ALIVE = 1;
    private static final int NOT_ALIVE = 0;

    private final DeviceLiveRepository deviceLiveRepository;
    private final DeviceBrokenRepository deviceBrokenRepository;
    private final DeviceAlarmRepository deviceAlarmRepository;

    @Autowired
    public AnalyticsServiceImpl(DeviceLiveRepository deviceLiveRepository,
                                DeviceBrokenRepository deviceBrokenRepository,
                                DeviceAlarmRepository deviceAlarmRepository) {
        this.deviceLiveRepository = deviceLiveRepository;
        this.deviceBrokenRepository = deviceBrokenRepository;
        this.deviceAlarmRepository = deviceAlarmRepository;
    }

    //=======================================================================
    @Override
    public void deviceAnalytics(Device device) {
        if (device.getIsAlive() == NOT_ALIVE) {
            System.out.println("Saved device as broken: " + device.getId());
            deviceBrokenRepository.save(deviceBrokenEntityBuilder(device));
        }
        if (device.getIsAlive() == ALIVE) {
            if (device.getAir_temperature() >= AIR_TEMPERATURE_MAX || device.getAir_temperature() <= AIR_TEMPERATURE_MIN
                    || device.getWater_temperature() >= WATER_TEMPERATURE_MAX || device.getWater_temperature() <= AIR_TEMPERATURE_MIN
                    || device.getPressure() >= PRESSURE_MAX || device.getPressure() <= PRESSURE_MIN
                    || device.getWind_speed() >= WIND_SPEED_MAX
                    || device.getWave_height() >= WAVE_HEIGHT_MAX) {
                deviceAlarmRepository.save(deviceAlarmEntityBuilder(device));
            } else {
                deviceLiveRepository.save(deviceLiveEntityBuilder(device));
            }
        }
    }

    //=======================================================================
    @Override
    public DeviceFullModel getNotifications() {
        List<Device> listAlarm = mapperDeviceAlarmList(deviceAlarmRepository.findAll());
        List<Device> listBroken = mapperDeviceBrokenList(deviceBrokenRepository.findAll());
        List<Device> listLive = mapperDeviceLiveList(deviceLiveRepository.findAll());
        return DeviceFullModel.builder()
                .alarm_list(listAlarm)
                .broken_list(listBroken)
                .live_list(listLive)
                .build();
    }

    //=======================================================================
    private List<Device> mapperDeviceLiveList(List<DeviceLiveEntity> devices) {
        List<Device> list = new ArrayList<>();
        devices.forEach(deviceLiveEntity -> list.add(mapperDevice(deviceLiveEntity)));
        return list;
    }

    //=======================================================================
    private List<Device> mapperDeviceAlarmList(List<DeviceAlarmEntity> devices) {
        List<Device> list = new ArrayList<>();
        devices.forEach(deviceAlarmEntity -> list.add(mapperDevice(deviceAlarmEntity)));
        return list;
    }

    //=======================================================================
    private List<Device> mapperDeviceBrokenList(List<DeviceBrokenEntity> devices) {
        List<Device> list = new ArrayList<>();
        devices.forEach(deviceBrokenEntity -> list.add(mapperDevice(deviceBrokenEntity)));
        return list;
    }

    //=======================================================================
    private Device mapperDevice(DeviceBrokenEntity device) {
        return Device.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }

    //=======================================================================
    private Device mapperDevice(DeviceAlarmEntity device) {
        return Device.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }

    //=======================================================================
    private Device mapperDevice(DeviceLiveEntity device) {
        return Device.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }

    //=======================================================================
    private DeviceLiveEntity deviceLiveEntityBuilder(Device device) {
        return DeviceLiveEntity.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }

    //=======================================================================
    private DeviceAlarmEntity deviceAlarmEntityBuilder(Device device) {
        return DeviceAlarmEntity.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }

    //=======================================================================
    private DeviceBrokenEntity deviceBrokenEntityBuilder(Device device) {
        return DeviceBrokenEntity.builder()
                .id(device.getId())
                .wind_speed(device.getWind_speed())
                .air_temperature(device.getAir_temperature())
                .water_temperature(device.getWater_temperature())
                .wave_height(device.getAir_temperature())
                .pressure(device.getPressure())
                .date_time(device.getDate_time())
                .isAlive(device.getIsAlive())
                .location(device.getLocation())
                .build();
    }
    //=======================================================================
}
