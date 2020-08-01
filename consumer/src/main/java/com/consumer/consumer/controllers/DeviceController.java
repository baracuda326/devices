package com.consumer.consumer.controllers;

import com.consumer.consumer.model.DeviceFullModel;
import com.consumer.consumer.services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DeviceController {
    private AnalyticsService analyticsService;

    @Autowired
    public DeviceController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/get/all")
    public DeviceFullModel getNotifications() {
        return analyticsService.getNotifications();
    }
}
