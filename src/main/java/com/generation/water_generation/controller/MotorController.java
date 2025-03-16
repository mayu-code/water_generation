package com.generation.water_generation.controller;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/motor")
public class MotorController {
    private final AtomicInteger speed = new AtomicInteger(0);
    private boolean isRunning = false;

    @GetMapping("/start")
    public String startMotor() {
        isRunning = true;
        return "START";
    }

    @GetMapping("/stop")
    public String stopMotor() {
        isRunning = false;
        speed.set(0);
        return "STOP";
    }

    @GetMapping("/speed")
    public String setSpeed(@RequestParam int value) {
        speed.set(value);
        return "SPEED:" + value;
    }

    @GetMapping("/status")
    public String getStatus() {
        return isRunning ? "RUNNING:" + speed.get() : "STOPPED";
    }
}
