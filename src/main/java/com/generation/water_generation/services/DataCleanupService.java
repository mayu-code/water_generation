package com.generation.water_generation.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.water_generation.repository.SensorDataRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class DataCleanupService {

    private final SensorDataRepository sensorDataRepository;

    public DataCleanupService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    // @Scheduled(cron = "0 * * * * *") // Runs every minute (for testing only)
    @Transactional
    public void deleteOldRecords() {
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);
        String cutoffDate = twoDaysAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        int deletedCount = sensorDataRepository.deleteByTimestampBefore(cutoffDate);
        // System.out.println("Deleted " + deletedCount + " old records.");
    }

}
