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
    private static boolean isDeviceOnline = false; // Track device status

    @GetMapping("/status")
    public Map<String, String> getDeviceStatus() {
        Map<String, String> response = new HashMap<>();

        if (isDeviceOnline) {
            response.put("status", "online");
        } else {
            response.put("status", "offline");
        }

        return response;
    }

    @PostMapping("/ping")
    public void updatePing() {
        lastPingTime = Instant.now(); // Update last ping time
        isDeviceOnline = true; // Mark device as online
    }

    @Scheduled(fixedRate = 5000) // Check every 5 seconds
    public void checkDeviceHealth() {
        long secondsSinceLastPing = Instant.now().getEpochSecond() - lastPingTime.getEpochSecond();

        if (secondsSinceLastPing > 10) { // If no ping in the last 10 seconds, mark offline
            isDeviceOnline = false;
            System.out.println("Device is offline!");
        }
    }
}
