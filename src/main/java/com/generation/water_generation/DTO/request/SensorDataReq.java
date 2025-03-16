package com.generation.water_generation.DTO.request;

import lombok.Data;

@Data
public class SensorDataReq {
    private Double temperature;
    private Double humidity;
    private Double waterLevel;
}
