package com.datasource.datasource.controllers;

import com.datasource.datasource.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DatasourceController {
    private DatasourceService datasourceService;

    @Autowired
    public DatasourceController(DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    @PostMapping("datasource/start")
    public void start(){
        datasourceService.start();
    }

    @PostMapping("datasource/stop")
    public String stop(){
        datasourceService.stop();
        return "Stopped.....";
    }
}
