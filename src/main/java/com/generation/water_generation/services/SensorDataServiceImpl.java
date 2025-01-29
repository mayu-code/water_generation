package com.generation.water_generation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.water_generation.entity.SensorData;
import com.generation.water_generation.repository.SensorDataRepository;

@Service
public class SensorDataServiceImpl implements SensorDataService {

    @Autowired
    private SensorDataRepository repository;

    @Override
    public SensorData addData(SensorData sensorData) {
        return this.repository.save(sensorData);
    }

    @Override
    public List<SensorData> getRecentSensorData() {
        return this.repository.findTop5ByOrderByTimestampDesc();
    }

    @Override
    public List<SensorData> getAllData() {
        // TODO Auto-generated method stub
        return this.repository.findAll();
    }

}
