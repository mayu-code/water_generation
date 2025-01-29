package com.generation.water_generation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String temperature;
    private String humidity;
    private String waterLevel;

}
