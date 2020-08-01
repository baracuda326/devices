package com.consumer.consumer.repository;

import com.consumer.consumer.model.documents.DeviceLiveEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceLiveRepository extends MongoRepository<DeviceLiveEntity, Integer> {
}
