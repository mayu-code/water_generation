package com.generation.water_generation.DTO.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SensorDataResponse {

    private String message;
    private HttpStatus status;
    private int statusCode;
}
