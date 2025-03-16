package com.generation.water_generation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.water_generation.entity.MotorStatus;
import com.generation.water_generation.repository.MotorStatusRepository;

@Service
public class MotorStatusServiceImpl implements MotorStatusService {

    @Autowired
    private MotorStatusRepository repository;

    @Override
    public String getStatus() {
        return repository.findById(1L).map(MotorStatus::getStatus).orElse("OFF");
    }

    @Override
    public void setStatus(String status) {
        MotorStatus motorStatus = repository.findById(1L).orElse(new MotorStatus());
        motorStatus.setStatus(status);
        repository.save(motorStatus);
    }

}
