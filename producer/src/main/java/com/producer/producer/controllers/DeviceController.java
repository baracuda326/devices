package com.producer.producer.controllers;

import com.producer.producer.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;


@RestController
@RequestMapping
public class DeviceController {
    private KafkaTemplate<String, Post> kafkaTemplate;
    private final static String TOPIC = "device";

    @Autowired
    public DeviceController(KafkaTemplate<String, Post> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("kafka/publish/{message}")
    public String post(@PathVariable("message") String message) {
        kafkaTemplate.send(TOPIC, new Post(message, "20.20.2020"));
        System.out.println("Hello from producer " + message);
        return "Hello from producer " + message;
    }
}
