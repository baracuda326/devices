package com.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.producer.model.Post;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class KafkaConsumer {
    private CountDownLatch latch = new CountDownLatch(1);
//    @KafkaListener(topics = "device", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }


        @KafkaListener(topics = "device", groupId = "group_json")
    public void consumeJson(String user) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Post post  = objectMapper.readValue(user, Post.class);
        System.out.println("Consumed JSON Message: " + post);
        latch.countDown();
    }
}
