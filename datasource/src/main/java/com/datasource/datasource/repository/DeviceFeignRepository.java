package com.datasource.datasource.repository;

import com.datasource.datasource.model.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "producer-alpha")
public interface DeviceFeignRepository {
    @PostMapping("kafka/publish")
    String post(@RequestBody Device post);
}
