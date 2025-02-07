package com.generation.water_generation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MotorStatus {
    @Id
    private Long id = 1L; // Single record
    private String status = "OFF";
}
