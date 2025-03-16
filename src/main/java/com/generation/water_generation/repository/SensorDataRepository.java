package com.generation.water_generation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.generation.water_generation.entity.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findTop5ByOrderByTimestampDesc();

    SensorData findTopByOrderByTimestampDesc();

    List<SensorData> findByOrderByTimestampDesc();

    @Modifying
    @Transactional
    @Query("DELETE FROM SensorData s WHERE s.timestamp < :cutoffDate")
    int deleteByTimestampBefore(@Param("cutoffDate") String cutoffDate);

}
