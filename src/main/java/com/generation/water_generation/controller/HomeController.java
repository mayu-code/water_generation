package com.generation.water_generation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.water_generation.DTO.request.SensorDataReq;
import com.generation.water_generation.entity.SensorData;
import com.generation.water_generation.services.SensorDataService;
import com.generation.water_generation.services.SensorDataServiceImpl;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SensorDataServiceImpl serviceImpl;

    @PostMapping("addData")
    public ResponseEntity<?> addData(@RequestBody SensorDataReq req) {

        SensorData sensorData = new SensorData();
        sensorData.setHumidity(req.getHumidity());
        sensorData.setTemperature(req.getTemperature());
        sensorData.setWaterLevel(req.getWaterLevel());

        try {
            this.serviceImpl.addData(sensorData);
            return ResponseEntity.ok(200);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
