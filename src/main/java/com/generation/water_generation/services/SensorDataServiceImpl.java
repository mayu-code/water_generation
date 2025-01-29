package com.generation.water_generation.services;

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

}
