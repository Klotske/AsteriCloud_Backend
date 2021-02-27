package com.asteri.server.controllers;

import com.asteri.server.requests.MetricsRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping
public class MetricsController {

    final MetricsRequest metricsRequest;

    public MetricsController(MetricsRequest metricsRequest) {
        this.metricsRequest = metricsRequest;
    }

    @GetMapping("/network")
    public Map<String,String> networkMonitoring(){
        Map<String,String> map = new HashMap<>();
        map.put("output",metricsRequest.getData("SYS.VPC","upstream_bandwidth"));
        map.put("input",metricsRequest.getData("SYS.VPC","downstream_bandwidth"));
        return map;
    }

    @GetMapping("/ecs")
    public Map<String,String> averageCPU(){
        Map<String,String> map = new HashMap<>();
        map.put("averageCPU",metricsRequest.getData("SYS.ECS","cpu_util"));
        return  map;
    }
}
