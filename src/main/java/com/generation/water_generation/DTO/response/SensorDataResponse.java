package com.generation.water_generation.DTO.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SensorDataResponse {

    private HttpStatus status;
}
