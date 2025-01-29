package com.generation.water_generation.DTO.request;

import lombok.Data;

@Data
public class SensorDataReq {
    private String temperature;
    private String humidity;
    private String waterLevel;
}
