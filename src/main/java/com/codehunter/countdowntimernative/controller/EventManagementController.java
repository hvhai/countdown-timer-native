package com.codehunter.countdowntimernative.controller;

import com.codehunter.countdowntimernative.business.EventService;
import com.codehunter.countdowntimernative.dto.EventDto;
import com.codehunter.countdowntimernative.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@RequiredArgsConstructor
@Slf4j
public class EventManagementController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getAllEvents() {
        log.info("getAllEvents by admin");
        return eventService.getAllEvent().stream().map(EventMapper::toEvent).toList();
    }
}
