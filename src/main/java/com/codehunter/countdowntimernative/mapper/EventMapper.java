package com.codehunter.countdowntimernative.mapper;

import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.dto.EventDto;

public class EventMapper {
    private EventMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static EventDto toEvent(Event newEvent) {
        return new EventDto(newEvent.getId().getValue(), newEvent.getName(), newEvent.getDate());
    }
}
