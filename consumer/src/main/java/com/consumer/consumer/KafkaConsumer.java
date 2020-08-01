package com.consumer.consumer;

import com.consumer.consumer.model.Device;
import com.consumer.consumer.services.AnalyticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private ObjectMapper objectMapper;
    private AnalyticsService analyticsService;
    @Autowired
    public KafkaConsumer(ObjectMapper objectMapper,AnalyticsService analyticsService) {
        this.objectMapper = objectMapper;
        this.analyticsService = analyticsService;
    }

    @KafkaListener(topics = "device", groupId = "group_json")
    public void consume(String message) throws JsonProcessingException {
        Device post = objectMapper.readValue(message, Device.class);
        System.out.println("Consumed message: " + post);
        analyticsService.deviceAnalytics(post);
    }

}
