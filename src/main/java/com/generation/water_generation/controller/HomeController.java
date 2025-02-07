package com.generation.water_generation.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.water_generation.DTO.request.SensorDataReq;
import com.generation.water_generation.DTO.response.DataResponse;
import com.generation.water_generation.DTO.response.MotorStatusResponse;
import com.generation.water_generation.DTO.response.SensorDataResponse;
import com.generation.water_generation.entity.SensorData;
import com.generation.water_generation.services.MotorStatusServiceImpl;
import com.generation.water_generation.services.SensorDataService;
import com.generation.water_generation.services.SensorDataServiceImpl;
import com.generation.water_generation.services.WaterExtractionCalculator;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @Autowired
    private SensorDataServiceImpl serviceImpl;

    @Autowired
    private MotorStatusServiceImpl motorStatusServiceImpl;

    @PostMapping("addData")
    public ResponseEntity<?> addData(@RequestBody SensorDataReq req) {

        SensorData sensorData = new SensorData();
        sensorData.setHumidity(req.getHumidity());
        sensorData.setTemperature(req.getTemperature());
        sensorData.setWaterLevel(req.getWaterLevel());

        // Get previous water level from the latest entry
        Double previousWaterLevel = serviceImpl.getLastWaterLevel();

        // Calculate water extracted (previous level - current level)
        if (previousWaterLevel != null) {
            double extracted = previousWaterLevel - req.getWaterLevel();
            sensorData.setWaterExtracted(extracted > 0 ? extracted : 0.0); // Only store positive values
        } else {
            sensorData.setWaterExtracted(0.0); // First entry is 0
        }

        // Get the values (already in percentage)
        double temperature = req.getTemperature(); // Temperature in Celsius
        double humidity = req.getHumidity(); // Humidity in percentage
        double waterLevel = req.getWaterLevel(); // Water level in percentage

        // Constant k for water generation estimation (adjust based on environment)
        double k = 0.1;

        // Calculate the water generated (waterExtracted) based on temperature,
        // humidity, and water level
        double waterGenerated = k * temperature * (1 - (humidity / 100)) * (waterLevel / 100);

        // Round to 2 decimal places using BigDecimal
        BigDecimal roundedWaterGenerated = new BigDecimal(waterGenerated).setScale(2, RoundingMode.HALF_UP);

        // Store the rounded water generated (extracted) value
        sensorData.setWaterExtracted(roundedWaterGenerated.doubleValue()); // Store in waterExtracted field

        SensorDataResponse res = new SensorDataResponse();

        try {
            this.serviceImpl.addData(sensorData);

            res.setMessage("Data added Successfully !!");
            res.setStatus(HttpStatus.OK);
            res.setStatusCode(200);

            return ResponseEntity.of(Optional.of(res));
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("Data addition failed !!");
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

    @GetMapping("/status")
    public ResponseEntity<?> getStatus() {

        MotorStatusResponse res = new MotorStatusResponse();
        String status = motorStatusServiceImpl.getStatus();

        res.setMessage("Status Get Successfully");
        res.setMotor(status);
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);

        return ResponseEntity.of(Optional.of(res));

    }

    @PostMapping("/toggle")
    public ResponseEntity<?> toggleLight() {

        MotorStatusResponse res = new MotorStatusResponse();

        String newStatus = motorStatusServiceImpl.getStatus().equals("OFF") ? "ON" : "OFF";
        motorStatusServiceImpl.setStatus(newStatus);

        res.setMessage("Status Changed Successfully");
        res.setMotor(newStatus);
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);

        return ResponseEntity.of(Optional.of(res));

    }

}
