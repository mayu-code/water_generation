package com.generation.water_generation.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
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

    private Double temperature;
    private Double humidity;
    private Double waterLevel;
    private Double waterExtracted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private String timestamp;

}
