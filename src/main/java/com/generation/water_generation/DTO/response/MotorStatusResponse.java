package com.generation.water_generation.DTO.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MotorStatusResponse {

    private String message;
    private int statusCode;
    private HttpStatus status;
    private String motor;
}
