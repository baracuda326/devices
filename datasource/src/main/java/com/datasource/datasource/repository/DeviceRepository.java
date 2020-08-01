package com.datasource.datasource.repository;

import com.datasource.datasource.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity,String> {
}
