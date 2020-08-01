package com.consumer.consumer.repository;

import com.consumer.consumer.model.documents.DeviceAlarmEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceAlarmRepository extends MongoRepository<DeviceAlarmEntity,Integer> {
}
