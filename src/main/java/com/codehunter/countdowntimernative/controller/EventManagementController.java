package com.codehunter.countdowntimernative.controller;

import com.codehunter.countdowntimernative.business.EventService;
import com.codehunter.countdowntimernative.dto.EventDto;
import com.codehunter.countdowntimernative.mapper.EventMapper;
import com.codehunter.countdowntimernative.response.ResponseDTO;
import com.codehunter.countdowntimernative.response.ResponseFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO<List<EventDto>>> getAllEvents() {
        log.info("getAllEvents by admin");
        List<EventDto> events = eventService.getAllEvent().stream().map(EventMapper::toEvent).toList();
        return ResponseFormatter.handleList(events);
    }
}
