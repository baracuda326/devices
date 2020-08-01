package com.consumer.consumer.services;

import com.consumer.consumer.model.Device;
import com.consumer.consumer.model.DeviceFullModel;

public interface AnalyticsService {
    void deviceAnalytics(Device device);

    DeviceFullModel getNotifications();
}
