package com.produceralpha.produceralpha.controllers;

import com.produceralpha.produceralpha.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DeviceController {
    private KafkaTemplate<String, Device> kafkaTemplate;
    private static final String TOPIC = "device";

    @Autowired
    public DeviceController(KafkaTemplate<String, Device> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("kafka/publish")
    public String post(@RequestBody Device post) {
        kafkaTemplate.send(TOPIC, post);
        return "Hello from producer " + post;
    }
}
