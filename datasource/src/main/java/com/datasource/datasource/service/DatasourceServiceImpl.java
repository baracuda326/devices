package com.datasource.datasource.service;

import com.datasource.datasource.model.Device;
import com.datasource.datasource.model.DeviceEntity;
import com.datasource.datasource.model.Location;
import com.datasource.datasource.repository.DeviceFeignRepository;
import com.datasource.datasource.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatasourceServiceImpl implements DatasourceService {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
    private boolean flag = true;
    private ObjectMapper objectMapper;
    private DeviceFeignRepository deviceFeignClient;
    private DeviceRepository deviceRepository;

    @Autowired
    public DatasourceServiceImpl(ObjectMapper objectMapper
            , DeviceFeignRepository deviceFeignClient, DeviceRepository deviceRepository) {
        this.objectMapper = objectMapper;
        this.deviceFeignClient = deviceFeignClient;
        this.deviceRepository = deviceRepository;
    }

    @PostConstruct
    public void init() {
        deviceRepository.saveAll(createData());
    }

    private List<DeviceEntity> createData() {
        List<DeviceEntity> list = new ArrayList<>();
        DeviceEntity device1 = new DeviceEntity(1, 1
                , 3.0f,2.0f, 25.0f, 27.0f, 740.0d, 1
                , new Location(33.025357d, 35.0069733d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device2 = new DeviceEntity(2, 2
                , 5.0f,3.0f, 24.0f, 28.0f, 741.0d, 1
                , new Location(32.997372d, 34.9825443d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device3 = new DeviceEntity(3, 3
                , 4.0f,2.5f, 26.7f, 27.2f, 742.1d, 1
                , new Location(32.965594d, 34.958764d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device4 = new DeviceEntity(4, 4
                , 7.0f,3.0f, 20.0f, 24.0f, 743.0d, 1
                , new Location(32.937117d, 34.929672d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device5 = new DeviceEntity(5, 5
                , 6.0f,4.2f, 22.0f, 23.0f, 750.0d, 1
                , new Location(32.905992d, 34.892593d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device6 = new DeviceEntity(6, 6
                , 40.0f,5.6f, 27.0f, 22.0f, 750.0d, 1
                , new Location(32.868801d, 34.864784d), LocalDateTime.now().format(dateTimeFormatter));
        DeviceEntity device7 = new DeviceEntity(7, 7
                , 100.0f,10.2f, 10.0f, 18.0f, 760.0d, 1
                , new Location(32.822651d, 34.848991d), LocalDateTime.now().format(dateTimeFormatter));
        list.add(device1);
        list.add(device2);
        list.add(device3);
        list.add(device4);
        list.add(device5);
        list.add(device6);
        list.add(device7);
        return list;
    }

    @Override
    public void start() {
        List<Device> list = mapper(deviceRepository.findAll());
        int count = 0;
        while (flag) {
            for (Device device : list) {
                try {
                    if (count >= 20 && device.getId() == 6) {
                        device.setAir_temperature(0);
                        device.setWater_temperature(0);
                        device.setPressure(0);
                        device.setWind_speed(0);
                        device.setDate_time(LocalDateTime.now().format(dateTimeFormatter));
                        device.setIsAlive(0);
                    }
                    sendData(device);
                    count++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendData(Device dto) throws IOException {
        System.out.println("Sended dto : " + dto);
        deviceFeignClient.post(dto);
    }

    private List<Device> mapper(List<DeviceEntity> list) {
        List<Device> devices = new ArrayList<>();
        for (DeviceEntity deviceEntity : list) {
            devices.add(Device.builder()
                    .id(deviceEntity.getId())
                    .wind_speed(deviceEntity.getWind_speed())
                    .wave_height(deviceEntity.getWave_height())
                    .water_temperature(deviceEntity.getWater_temperature())
                    .air_temperature(deviceEntity.getAir_temperature())
                    .pressure(deviceEntity.getPressure())
                    .isAlive(deviceEntity.getIsAlive())
                    .location(deviceEntity.getLocation())
                    .date_time(deviceEntity.getDate_time())
                    .build());
        }
        return devices;
    }

    @Override
    public void stop() {
        flag = false;
    }

}
