package com.generation.water_generation.services;

import java.time.LocalDateTime;
import java.util.List;

import com.generation.water_generation.entity.SensorData;

public class WaterExtractionCalculator {

    public static double calculateWaterExtracted(List<SensorData> dataList, double tankCapacity) {
        if (dataList.size() < 2)
            return 0;

        SensorData first = dataList.get(0);
        SensorData last = dataList.get(dataList.size() - 1);

        double initialWaterLevel = (Double.valueOf(first.getWaterLevel()) / 100) * tankCapacity;
        double finalWaterLevel = (Double.valueOf(last.getWaterLevel()) / 100) * tankCapacity;

        double temperature = Double.valueOf(last.getTemperature());
        double humidity = Double.valueOf(last.getHumidity());

        double evaporationLoss = 0.1 * (temperature - (humidity / 10));

        return (initialWaterLevel - finalWaterLevel) + evaporationLoss;
    }
}
