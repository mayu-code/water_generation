package com.generation.water_generation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.water_generation.entity.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

}
