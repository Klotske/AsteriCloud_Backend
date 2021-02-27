package com.asteri.hackathon102.controllers;


import com.asteri.hackathon102.requests.MetricsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class StorageController {

    final MetricsRequest metricsRequest;

    @Autowired
    public StorageController(MetricsRequest metricsRequest) {
        this.metricsRequest = metricsRequest;
    }

    @GetMapping("/storage")
    public Map<String,String> readWrite() {
        Map<String, String> map = new HashMap<>();
        map.put("read",metricsRequest.getData("SYS.EVS","disk_device_read_bytes_rate"));
        map.put("write",metricsRequest.getData("SYS.EVS","disk_device_write_bytes_rate"));
        return map;
    }
    @GetMapping("/storageRequests")
    public Map<String,String> requests() {
        Map<String, String> map = new HashMap<>();
        map.put("read",metricsRequest.getData("SYS.EVS","disk_device_read_requests_rate"));
        map.put("write",metricsRequest.getData("SYS.EVS","disk_device_write_requests_rate"));
        return map;
    }
}
