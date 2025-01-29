package com.generation.water_generation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.water_generation.DTO.request.SensorDataReq;
import com.generation.water_generation.DTO.response.DataResponse;
import com.generation.water_generation.DTO.response.SensorDataResponse;
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

        SensorDataResponse res = new SensorDataResponse();

        try {
            this.serviceImpl.addData(sensorData);

            res.setMessage("Data added Successfully !!");
            res.setStatus(HttpStatus.OK);
            res.setStatusCode(200);

            return ResponseEntity.of(Optional.of(res));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            res.setMessage("Data add faild !!");
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentSensorData() {
        List<SensorData> recentSensorData = this.serviceImpl.getRecentSensorData();

        DataResponse res = new DataResponse();

        res.setMessage("Data Fetch Successfully !!");
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);
        res.setData(recentSensorData);

        return ResponseEntity.of(Optional.of(res));

    }

    @GetMapping("/allData")
    public ResponseEntity<?> getAllSensorData() {
        List<SensorData> allData = this.serviceImpl.getAllData();

        DataResponse res = new DataResponse();

        res.setMessage("Data Fetch Successfully !!");
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);
        res.setData(allData);

        return ResponseEntity.of(Optional.of(res));

    }

}
