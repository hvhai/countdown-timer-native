package com.codehunter.countdowntimernative;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @GetMapping
    public String getAllEvents() {
        return "allEvents";
    }
}
