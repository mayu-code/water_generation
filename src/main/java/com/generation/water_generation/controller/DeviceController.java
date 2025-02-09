package com.generation.water_generation.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

    private static Instant lastPingTime = Instant.now(); // Track last ping

    @GetMapping("/status")
    public Map<String, String> getDeviceStatus() {
        Map<String, String> response = new HashMap<>();

        long secondsSinceLastPing = Instant.now().getEpochSecond() - lastPingTime.getEpochSecond();

        if (secondsSinceLastPing <= 2) { // If last ping was within 10 seconds, it's online
            response.put("status", "online");
        } else {
            response.put("status", "offline");
        }

        return response;
    }

    @PostMapping("/ping")
    public void updatePing() {
        lastPingTime = Instant.now(); // Update last ping time
    }

    @Scheduled(fixedRate = 10000) // Reset status if no ping in 10 seconds
    public void checkDeviceHealth() {
        if (Instant.now().getEpochSecond() - lastPingTime.getEpochSecond() > 10) {
            System.out.println("Device is offline!");
        }
    }
}
