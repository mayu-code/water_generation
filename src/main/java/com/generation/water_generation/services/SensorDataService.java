package com.generation.water_generation.services;

import java.util.List;

import com.generation.water_generation.entity.SensorData;

public interface SensorDataService {

    SensorData addData(SensorData sensorData);

    List<SensorData> getRecentSensorData();

    List<SensorData> getAllData();

    Double getLastWaterLevel();
}
