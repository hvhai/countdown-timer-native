package com.codehunter.countdowntimernative.controller;

import com.codehunter.countdowntimernative.business.EventService;
import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.dto.CreateEventDto;
import com.codehunter.countdowntimernative.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvent().stream().map(EventController::toEvent).toList();
    }

    @PostMapping
    public EventDto creatEvent(@RequestBody CreateEventDto createEventDto) {
        Event event = Event.builder().name(createEventDto.name())
                .date(createEventDto.dateTime())
                .build();
        Event newEvent = eventService.createEvent(event);
        return toEvent(newEvent);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    private static EventDto toEvent(Event newEvent) {
        return new EventDto(newEvent.getId().getValue(), newEvent.getName(), newEvent.getDate());
    }
}
