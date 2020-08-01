package com.consumer.consumer.repository;

import com.consumer.consumer.model.documents.DeviceBrokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceBrokenRepository extends MongoRepository<DeviceBrokenEntity,Integer> {
}
