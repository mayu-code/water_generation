package com.generation.water_generation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.water_generation.entity.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findTop5ByOrderByTimestampDesc();
}
